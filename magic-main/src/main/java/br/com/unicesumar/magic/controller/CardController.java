package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.enums.CardType;
import br.com.unicesumar.magic.repository.CardRepository;
import br.com.unicesumar.magic.repository.DeckRepository;
import br.com.unicesumar.magic.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private DeckRepository deckRepository;

    @PostMapping("/commander")
    public ResponseEntity getCommander(@RequestBody Card name, @RequestParam int quantidadeCartas) {
        Card response = cardService.getCommanderCard(name.getName());

        if (response.getCardType().equals(CardType.COMMANDER)) {
            Deck deck = new Deck();
            deck.setCommander(response);
            deck.setCards(cardService.getCommonCard(quantidadeCartas, response.getColors()));

            deckRepository.save(deck);
            saveCardsToFile(deck, "src/main/resources/deck.json");
            response.setResponse("Carta adicionada com sucesso!");

            return ResponseEntity.ok(response);
        }
        response.setResponse("Essa carta não pode ser a comandante!");

        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/allDecks")
    public void listDecks() {

    }

    @GetMapping("/login/userDecks")
    @Cacheable("cacheAllDecksUserLogged")
    public void listDecksUserLogged() {

    }

    @GetMapping("/send/deck")
    public void sendDeck() {

    }

    public void saveCardsToFile(Deck deck, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), deck);
            System.out.println("Cartas salvas com sucesso em " + filePath + "!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar cartas em arquivo JSON!");
        }
    }
}
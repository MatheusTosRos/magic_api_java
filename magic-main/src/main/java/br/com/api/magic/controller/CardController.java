package br.com.api.magic.controller;

import br.com.api.magic.entity.Card;
import br.com.api.magic.entity.Deck;
import br.com.api.magic.enums.CardType;
import br.com.api.magic.repository.DeckRepository;
import br.com.api.magic.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DeckRepository deckRepository;

    @PostMapping("/commander")
    public ResponseEntity<?> getCommander(@RequestBody Card name, @RequestParam int quantidadeCartas) {
        Card response = cardService.getCommanderCard(name.getName());

        if (response.getCardType().equals(CardType.COMMANDER)) {
            Deck deck = new Deck();
            deck.setCommander(response);
            deck.setCards(cardService.getCommonCard(quantidadeCartas, response.getColors()));

            deckRepository.save(deck);
            saveCardsToFile(deck, "magic-main/src/main/resources/examples/deck.json");
            response.setResponse("Carta adicionada com sucesso!");

            return ResponseEntity.ok(response);
        }
        response.setResponse("Essa carta não pode ser a comandante!");

        return ResponseEntity.badRequest().body(response);
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
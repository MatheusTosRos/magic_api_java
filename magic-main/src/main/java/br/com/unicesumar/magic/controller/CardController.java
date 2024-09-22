package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.enums.CardType;
import br.com.unicesumar.magic.repository.CardRepository;
import br.com.unicesumar.magic.repository.DeckRepository;
import br.com.unicesumar.magic.service.CardService;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/commander")
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

    public void saveCardsToFile(Deck deck, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), deck);
            System.out.println("Cartas salvas com sucesso em " + filePath + "!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar cartas em arquivo JSON");
        }
    }
}

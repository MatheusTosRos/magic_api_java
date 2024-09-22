package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.repository.DeckRepository;
import br.com.unicesumar.magic.repository.UsuarioRepository;
import br.com.unicesumar.magic.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class DeckController {

    @Autowired
    private DeckService deckService;
    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/allDecks")
    public ResponseEntity listDecks() {
        try {
            deckService.listarDeks();
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um problema ao listar todos os decks! " +
                    "Verifique se seu usuário é ADMIN! " + e);
        }
    }

    @GetMapping("/login/userDecks")
    @Cacheable("cacheAllDecksUserLogged")
    public ResponseEntity listDecksUserLogged() {
        try {
            deckService.listarDecksUsuarioLogado();
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível encontrar o deck do usuário! " + e);
        }
    }

    @PostMapping("/send/deck")
    public ResponseEntity sendDeck() {
        try {
            deckService.importarDecks();
            return ResponseEntity.status(HttpStatus.CREATED).body("Deck enviado com sucesso!");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O deck enviado está incorreto! " + e);
        }
    }
}

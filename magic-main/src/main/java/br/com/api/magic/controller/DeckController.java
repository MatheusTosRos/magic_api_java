package br.com.api.magic.controller;

import br.com.api.magic.entity.Deck;
import br.com.api.magic.entity.Usuario;
import br.com.api.magic.enums.UsuarioRole;
import br.com.api.magic.repository.DeckRepository;
import br.com.api.magic.repository.UsuarioRepository;
import br.com.api.magic.service.DeckService;
import br.com.api.magic.service.UsuarioService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/deck")
public class DeckController {

    @Autowired
    private DeckService deckService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private UsuarioRole usuarioRole;
    private ObjectMapper objectMapper;

    @GetMapping("/allDecks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listDecks(String usuarioId) {
        try {
            if (!usuarioService.EhAdmin(usuarioId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para acessar esta rota!");
            }

            List<Deck> decks = deckService.listarDecks();
            return ResponseEntity.ok(decks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ocorreu um problema ao listar todos os decks! " + e.getMessage());
        }
    }

    @GetMapping("/login/userDecks")
    @Cacheable("cacheAllDecksUserLogged")
    public ResponseEntity<?> listDecksUserLogged(@RequestHeader String Authorization) {
        try {
            List<Deck> decks = deckService.listarDecksUsuario(Authorization);
            return ResponseEntity.ok(decks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível encontrar o deck do usuário! " + e.getMessage());
        }
    }

    @PostMapping("/send/deck")
    public ResponseEntity importDeck(@RequestBody Deck deck) {
            try {
                Deck savedDeck = deckService.importarDecks(deck);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedDeck);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + e.getMessage());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao importar o arquivo JSON: " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}

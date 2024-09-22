package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.repository.DeckRepository;
import br.com.unicesumar.magic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;

public class DeckController {

    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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
}

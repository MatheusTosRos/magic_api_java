package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.dto.AuthenticationDTO;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.entity.Usuario;
import br.com.unicesumar.magic.enums.UsuarioRole;
import br.com.unicesumar.magic.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    public List<Deck> listarDecks() {

        return new ArrayList<>();
    }

    public void listarDecksUsuarioLogado() {

    }

    public void importarDecks() {

    }
}

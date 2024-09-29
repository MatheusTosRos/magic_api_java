package br.com.api.magic.service;

import br.com.api.magic.entity.Deck;
import br.com.api.magic.entity.Usuario;
import br.com.api.magic.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private JwtTokenService jwtTokenService;

    public List<Deck> listarDecks() {
        return deckRepository.findAll();
    }

    public List<Deck> listarDecksUsuario(String usuario) {
        return deckRepository.findByUsuarioId(this.jwtTokenService.getUserByToken(usuario.replace("Bearer ", "")));
    }

    public void importarDecks(List<Deck> decks) {

    }
}

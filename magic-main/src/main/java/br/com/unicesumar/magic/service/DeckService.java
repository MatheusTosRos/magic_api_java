package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.dto.AuthenticationDTO;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.entity.Usuario;
import br.com.unicesumar.magic.enums.UsuarioRole;
import br.com.unicesumar.magic.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeckService {

    @Autowired
    private Usuario usuario;
    @Autowired
    private UsuarioRole usuarioRole;
    @Autowired
    private DeckRepository deckRepository;


    public List<Deck> listarDeks() {
        var papel = usuario.getRole();

        if (papel.equals(usuarioRole)) {
            return deckRepository.findAll();
        }
        return null;
    }

    public void listarDecksUsuarioLogado() {

    }

    public void importarDecks() {

    }
}

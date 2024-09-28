package br.com.api.magic.service;

import br.com.api.magic.entity.Usuario;
import br.com.api.magic.enums.UsuarioRole;
import br.com.api.magic.repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean EhAdmin(String usuarioId) throws Exception {
        try {
            ObjectId id = new ObjectId(usuarioId);
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                return usuario.getRole() == UsuarioRole.ADMIN;
            } else {
                throw new Exception("Usuário não encontrado!");
            }
        } catch (IllegalArgumentException e) {
            throw new Exception("ID inválido!", e);
        } catch (Exception e) {
            throw new Exception("Erro ao verificar se o usuário é admin!", e);
        }
    }

    public Usuario getUsuarioLogado() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new Exception("Usuário não está autenticado!");
        }

        String login = authentication.getName();
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

        return usuario.orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }
}
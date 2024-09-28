package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.entity.Usuario;
import br.com.unicesumar.magic.enums.UsuarioRole;
import br.com.unicesumar.magic.repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
}
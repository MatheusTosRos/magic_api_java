package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.entity.Usuario;
import br.com.unicesumar.magic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = repository.findByLogin(username); // Use Optional<Usuario>

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o login: " + username);
        }
    }
}
package br.com.api.magic.dto;

import br.com.api.magic.enums.UsuarioRole;

public record CreateUserDTO(

        String login,
        String password,
        UsuarioRole role
) {
}
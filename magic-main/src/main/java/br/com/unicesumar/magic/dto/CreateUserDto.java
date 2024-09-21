package br.com.unicesumar.magic.dto;

import br.com.unicesumar.magic.enums.UsuarioRole;

public record CreateUserDto(

        String login,
        String password,
        UsuarioRole role
) {
}
package br.com.api.magic.enums;

import lombok.Getter;

@Getter
public enum UsuarioRole {

    ADMIN("admin"),
    USER("user");

    public final String role;

    UsuarioRole(String role) {
        this.role = role;
    }
}

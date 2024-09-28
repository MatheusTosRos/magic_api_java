package br.com.api.magic.dto;

public record AuthenticationDTO (
        String login,
        String password
) {
}
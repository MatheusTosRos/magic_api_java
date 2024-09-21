package br.com.unicesumar.magic.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScryfallResponse {
    private List<Card> data;

    // Getters and setters
}
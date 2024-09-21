package br.com.unicesumar.magic.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "deck")
public class Deck {

    private Card commander;

    private List<Card> cards;
}

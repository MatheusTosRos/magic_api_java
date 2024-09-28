package br.com.api.magic.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "deck")
public class Deck {
    private ObjectId usuarioId;
    private String userId;
    private Card commander;
    private List<Card> cards;
}

package br.com.unicesumar.magic.entity;


import br.com.unicesumar.magic.enums.CardType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "card")
public class Card {

    @Setter
    @Transient
    private String response;

    @Id
    private String id;

    private String name;

    private String type_line;

    private List<String> colors;

    @Setter
    private CardType cardType;




//    public void setCardType(){
//
//        if(this.type_line.startsWith("Legendary Creature")){
//            this.cardType = CardType.COMMANDER;
//        }else {
//            this.cardType = CardType.COMMON;
//        }
//    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type_line='" + type_line + '\'' +
                ", colors=" + colors +
                ", cardType=" + cardType +
                '}';
    }
}

package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.entity.ScryfallResponse;
import br.com.unicesumar.magic.enums.CardType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static br.com.unicesumar.magic.config.RestTemplateConfig.restTemplate;

@Service
public class CardService {

    private final String API_URL = "https://api.scryfall.com/cards";
    private final String API_URL_NAMED = "/named?fuzzy=";
    private final String API_URL_CARDS = "/search?q=color:%s&unique=cards&page=";


    private List<Card> cards = new ArrayList<>();

    public Card getCommanderCard(String name) {

        String url = API_URL + API_URL_NAMED + name;
        Card commander = restTemplate().getForObject(url, Card.class);

        if (commander.getType_line().startsWith("Legendary Creature")) {
            commander.setCardType(CardType.COMMANDER);
        } else {
            commander.setCardType(CardType.COMMON);
        }

        return commander;
    }

    public List<Card> getCommonCard(int qntdCard, List<String> colors) {

        List<Card> commonCards = new ArrayList<>();
        int page = 1;
        try {


            while (commonCards.size() < qntdCard) {
                String url = String.format(API_URL + API_URL_CARDS, String.join(",", colors)) + page;
                ScryfallResponse response = restTemplate().getForObject(url, ScryfallResponse.class);
                if (response != null && response.getData() != null) {
                    for (Card card : response.getData()) {
                        if (commonCards.size() >= qntdCard) {
                            break;
                        }
                        commonCards.add(card);
                    }
                }
                page++;
            }

            return commonCards;


        } catch (HttpClientErrorException.UnprocessableEntity e) {
          throw  new RuntimeException("cards insuficientes dessa cor para completar o deck");
        }
    }
}




package br.com.unicesumar.magic;

import static org.junit.jupiter.api.Assertions.*;

import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Test
    public void testGetCommonCard() {
        List<String> colors = Arrays.asList("R", "B");
        List<Card> cards = cardService.getCommonCard(10, colors);

        assertNotNull(cards);
        assertEquals(10, cards.size());
    }
}
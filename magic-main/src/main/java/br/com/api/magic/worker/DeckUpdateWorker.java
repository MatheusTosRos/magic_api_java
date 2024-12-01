package br.com.api.magic.worker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeckUpdateWorker {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "deck_updates_queue")
    public void handleDeckUpdate(String message) {
        System.out.println("Notificação de atualização do baralho: " + message);

        messagingTemplate.convertAndSend("/topic/deck-updates", message);
    }
}
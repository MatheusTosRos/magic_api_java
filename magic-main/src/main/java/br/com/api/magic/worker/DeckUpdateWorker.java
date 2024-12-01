package br.com.api.magic.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeckUpdateWorker {

    private static final Logger logger = LoggerFactory.getLogger(DeckUpdateWorker.class);
    private static final String WEBSOCKET_DESTINATION = "/topic/deck-updates";

    private final SimpMessagingTemplate messagingTemplate;

    public DeckUpdateWorker(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "deck_updates_queue")
    public void handleDeckUpdate(String message) {
        try {
            logger.info("Mensagem recebida na fila 'deck_updates_queue': {}", message);

            messagingTemplate.convertAndSend(WEBSOCKET_DESTINATION, message);
            logger.info("Mensagem enviada ao WebSocket no destino '{}': {}", WEBSOCKET_DESTINATION, message);

        } catch (Exception e) {
            logger.error("Erro ao processar mensagem de atualização do baralho: {}", e.getMessage(), e);
        }
    }
}

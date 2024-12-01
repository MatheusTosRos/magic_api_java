package br.com.api.magic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationWorkerService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationWorkerService.class);
    private static final String WEBSOCKET_DESTINATION = "/topic/deckUpdates";

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationWorkerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "deck_updates_queue")
    public void notifyDeckUpdate(String updateMessage) {
        try {
            messagingTemplate.convertAndSend(WEBSOCKET_DESTINATION, updateMessage);
            logger.info("Mensagem enviada para WebSocket: {}", updateMessage);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem para WebSocket: {}", e.getMessage(), e);
        }
    }
}

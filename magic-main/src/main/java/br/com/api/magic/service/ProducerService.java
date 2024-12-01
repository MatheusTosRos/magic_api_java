package br.com.api.magic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.queue.notification:notificationQueue}")
    private String notificationQueueName;

    @Value("${rabbitmq.queue.deckUpdates:deck_updates_queue}")
    private String deckUpdatesQueueName;

    public ProducerService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendDeckUpdateMessage(String message) {
        try {
            amqpTemplate.convertAndSend(deckUpdatesQueueName, message);
            logger.info("Mensagem de atualização enviada para a fila '{}': {}", deckUpdatesQueueName, message);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem de atualização: {}", e.getMessage(), e);
        }
    }

    public void sendNotification(String message) {
        try {
            amqpTemplate.convertAndSend(notificationQueueName, message);
            logger.info("Mensagem de notificação enviada para a fila '{}': {}", notificationQueueName, message);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem de notificação: {}", e.getMessage(), e);
        }
    }
}

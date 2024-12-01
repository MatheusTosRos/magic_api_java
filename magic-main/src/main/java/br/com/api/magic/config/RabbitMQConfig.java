package br.com.api.magic.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.notification:notificationQueue}")
    private String notificationQueueName;

    @Value("${rabbitmq.queue.deckImport:deck_import_queue}")
    private String deckImportQueueName;

    @Value("${rabbitmq.queue.deckUpdates:deck_updates_queue}")
    private String deckUpdatesQueueName;

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationQueueName, true);
    }

    @Bean
    public Queue deckImportQueue() {
        return new Queue(deckImportQueueName, true);
    }

    @Bean
    public Queue deckUpdatesQueue() {
        return new Queue(deckUpdatesQueueName, true);
    }
}

package com.partnest.virusscan.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQConfigProperties rabbitMQConfigProperties;
    private final RabbitMQConsumer rabbitMQConsumer;


    @Bean
    public Queue queue() {
        return new Queue(rabbitMQConfigProperties.queue());
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(rabbitMQConfigProperties.exchange());
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(rabbitMQConfigProperties.routing().key())
                .noargs();
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter
            ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(rabbitMQConfigProperties.queue());
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMQConsumer rabbitMQConsumer) {
        return new MessageListenerAdapter(rabbitMQConsumer, "receiveMessage");
    }
}

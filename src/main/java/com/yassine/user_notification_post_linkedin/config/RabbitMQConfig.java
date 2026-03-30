package com.yassine.user_notification_post_linkedin.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FOLLOW_QUEUE = "follow-queue";
    public static final String FOLLOW_ROUTING_KEY = "follow-routing-key";

    public static final String ACCEPTED_FOLLOW_QUEUE = "accepted-follow-queue";
    public static final String ACCEPTED_FOLLOW_ROUTING_KEY = "accepted-follow-routing-key";

    public static final String EXCHANGE = "follow-exchange";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        factory.setDefaultRequeueRejected(false);

        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public Queue followQueue() {
        return QueueBuilder.durable(FOLLOW_QUEUE).build();
    }

    @Bean
    public Queue accpetedFollowQueue() {
        return QueueBuilder.durable(ACCEPTED_FOLLOW_QUEUE).build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding followBinding(Queue followQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(followQueue)
                .to(exchange)
                .with(FOLLOW_ROUTING_KEY);
    }

    @Bean
    public Binding acceptedFollowBinding(Queue accpetedFollowQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(accpetedFollowQueue)
                .to(exchange)
                .with(ACCEPTED_FOLLOW_ROUTING_KEY);
    }
}
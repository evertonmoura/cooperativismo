package com.cooperativismo.config;

import com.cooperativismo.impl.rabbit.Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.spring.rabbitmq.queue}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Queue votacaoFinalizada() {
        return new Queue("sessaoecnerrada");
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }

}

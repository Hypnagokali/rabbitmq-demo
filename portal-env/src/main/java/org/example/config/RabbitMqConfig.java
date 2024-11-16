package org.example.config;

import com.rabbitmq.stream.Environment;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

@Configuration
public class RabbitMqConfig {

    @Bean
    RabbitStreamTemplate analysisCommandsStreamTemplate(Environment env) {
        RabbitStreamTemplate rabbitStreamTemplate = new RabbitStreamTemplate( env, "analysis.commands" );
        rabbitStreamTemplate.setMessageConverter( new Jackson2JsonMessageConverter() );
        return rabbitStreamTemplate;
    }

}

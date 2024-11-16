package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

@Configuration
public class RabbitMqConfig {


    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    RabbitStreamTemplate commandsTemplate(Environment env) {
        RabbitStreamTemplate rabbitStreamTemplate = new RabbitStreamTemplate( env, "analysis.commands" );
        rabbitStreamTemplate.setMessageConverter( new Jackson2JsonMessageConverter() );
        rabbitStreamTemplate.setProducerCustomizer( (name, builder) -> builder.name( "test" ) );

        return rabbitStreamTemplate;
    }

    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> analysisCommandsListener(Environment env) {
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(env);
        factory.setNativeListener(true);

        factory.setConsumerCustomizer((id, builder) -> {
            builder.name("analysisCommandsListener")
                .offset( OffsetSpecification.offset( loadOffset() ))
                .manualTrackingStrategy();
        });

        return factory;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    private long loadOffset() {
        return 20;
    }

}

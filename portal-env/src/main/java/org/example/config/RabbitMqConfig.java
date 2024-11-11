package org.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue analysisCommands() {
        return QueueBuilder.durable("analysis.commands")
            .withArgument( "x-queue-type", "stream" )
            .build();
    }

    @Bean
    public Queue analysisEvents() {
        return QueueBuilder.durable("analysis.events")
            .withArgument( "x-queue-type", "stream" )
            .build();
    }

    @Bean
    public Exchange analysisExchange() {
        return new DirectExchange( "analysis" );
    }

    @Bean
    public Binding createAnalysisCommandBinding(@Qualifier("analysisCommands") Queue analysisCommands,
                                           @Qualifier("analysisExchange") Exchange analysisExchange) {
        return BindingBuilder.bind( analysisCommands ).to( analysisExchange ).with( "analysis.create" ).noargs();
    }

    @Bean
    public Binding startAnalysisCommandBinding(@Qualifier("analysisCommands") Queue analysisCommands,
                                           @Qualifier("analysisExchange") Exchange analysisExchange) {
        return BindingBuilder.bind( analysisCommands ).to( analysisExchange ).with( "analysis.start" ).noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        var template = new RabbitTemplate( connectionFactory );
        template.setMessageConverter( new Jackson2JsonMessageConverter() );
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

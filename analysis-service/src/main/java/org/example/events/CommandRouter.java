package org.example.events;

import lombok.RequiredArgsConstructor;
import org.example.commands.StartAnalysisCommand;
import org.example.service.AnalysisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandRouter {

    private final AnalysisService analysisService;

    @RabbitListener(queues = "analysis.commands")
    public void handleStartCommand(@Payload StartAnalysisCommand command) {
        analysisService.startAnalysis( command.customerId(), command.userId(), command.analysisId() );
    }
}

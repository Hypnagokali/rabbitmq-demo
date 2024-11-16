package org.example.events;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import lombok.RequiredArgsConstructor;
import org.example.commands.AnalysisCommand;
import org.example.commands.CommandType;
import org.example.service.AnalysisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandRouter {

    private final AnalysisService analysisService;
    private final ObjectMapper om;

    @RabbitListener(queues = "analysis.commands", containerFactory = "analysisCommandsListener")
    public void handleStartCommand(Message message) {
        System.out.println("Received a message with length " + message.getBody());
        String command = new String( message.getBodyAsBinary(), StandardCharsets.UTF_8 );
        AnalysisCommand analysisCommand = parseCommand( command );

        if (analysisCommand.commandType() == CommandType.START) {
            analysisService.startAnalysis(analysisCommand.customerId(), analysisCommand.userId(), analysisCommand.analysisId());
        }

    }

    private AnalysisCommand parseCommand(String command) {
        AnalysisCommand analysisCommand;
        try {
            analysisCommand = om.readValue( command, AnalysisCommand.class );
        }
        catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }
        return analysisCommand;
    }
}

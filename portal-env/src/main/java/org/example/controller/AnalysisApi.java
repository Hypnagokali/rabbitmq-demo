package org.example.controller;

import org.example.commands.AnalysisCommand;
import org.example.commands.CommandType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis-api")
public class AnalysisApi {

    private final RabbitStreamTemplate rabbitStreamTemplate;

    public AnalysisApi(@Qualifier("analysisCommandsStreamTemplate") RabbitStreamTemplate rabbitStreamTemplate) {
        this.rabbitStreamTemplate = rabbitStreamTemplate;
    }

    @PostMapping("/{analysisId}/start")
    void startAnalysis(@PathVariable long analysisId) {
        AnalysisCommand analysisCommand = new AnalysisCommand( 1, 2, analysisId, CommandType.START);
        rabbitStreamTemplate.convertAndSend(
            analysisCommand
        );
    }

}

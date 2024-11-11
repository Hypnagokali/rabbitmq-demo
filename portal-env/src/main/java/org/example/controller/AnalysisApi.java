package org.example.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis-api")
@RequiredArgsConstructor
public class AnalysisApi {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/{analysisId}/start")
    void startAnalysis(@PathVariable long analysisId) {
        StartAnalysisCommand startAnalysisCommand = new StartAnalysisCommand( 1, 2, analysisId );
        rabbitTemplate.convertAndSend("analysis", "analysis.start", startAnalysisCommand);
    }


    @Getter
    @RequiredArgsConstructor
    static class StartAnalysisCommand {
        private final long customerId;
        private final long userId;
        private final long analysisId;
    }

}

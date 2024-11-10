package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis-api")
@RequiredArgsConstructor
public class AnalysisApi {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/start")
    void startAnalysis() {
        rabbitTemplate.convertAndSend("amq.direct", "event1", "Test Message");
    }

}

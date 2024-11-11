package org.example.commands;

public record StartAnalysisCommand (long customerId, long userId, long analysisId) {
}
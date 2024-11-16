package org.example.commands;

public record AnalysisCommand(long customerId, long userId, long analysisId, CommandType commandType) {
}
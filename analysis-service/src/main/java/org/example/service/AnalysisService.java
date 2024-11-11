package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnalysisService {

    public void startAnalysis(long customerId, long userId, long analysisId) {
        log.info( "Start analysis with ID = {}", analysisId );
        doStart();
        log.info( "Analysis started" );
    }

    private static void doStart() {
        try {
            Thread.sleep( 3000L );
        }
        catch ( InterruptedException e ) {
            throw new RuntimeException( e );
        }
    }
}

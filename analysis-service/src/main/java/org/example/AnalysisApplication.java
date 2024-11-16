package org.example;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalysisApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run( AnalysisApplication.class, args);
    }

    @SuppressWarnings("resource")
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Environment env = Environment.builder().build();
//
//        env.consumerBuilder()
//            .stream( "analysis.commands" )
//            .offset( OffsetSpecification.offset(20) )
//            .messageHandler( (c, m) -> {
//                System.out.println( "Raw message: " + m.getBody() );
//            } ).build();
    }

}
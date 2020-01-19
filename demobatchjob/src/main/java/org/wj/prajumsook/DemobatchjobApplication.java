package org.wj.prajumsook;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DemobatchjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemobatchjobApplication.class, args);
    }

}

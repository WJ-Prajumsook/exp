package org.wj.prajumsook.reservation.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.wj.prajumsook.reservation.*")
@EnableJpaRepositories(basePackages = "org.wj.prajumsook.reservation.*")
@EntityScan(basePackages = "org.wj.prajumsook.reservation.*")
public class ReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
    }
}

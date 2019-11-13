package org.wj.prajumsook.profile.demoprofile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")
class DemoprofileApplicationTests {

    @Autowired
    ApplicationService applicationService;

    @Test
    void contextLoads() {
        System.out.println(applicationService.message());
    }

}

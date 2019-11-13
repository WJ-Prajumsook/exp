package org.wj.prajumsook.logging.demologging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    DatabaseConfiguration databaseConfiguration;

    @GetMapping("/status")
    public String status() {

        logger.info("This is info log message: " + databaseConfiguration.getUrl());
        logger.warn("This is warn log message: " + databaseConfiguration.getUsername());
        logger.error("This is error log message: " + databaseConfiguration.getPassword());

        return "UP and RUNNING";
    }

    @PostMapping("/status")
    public String putStatus(@RequestBody String message) {
        return message;
    }

}

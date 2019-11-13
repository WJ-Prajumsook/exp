package org.wj.prajumsook.condition.democondition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.wj.prajumsook.condition.democondition.condition.MongoDBCondition;
import org.wj.prajumsook.condition.democondition.condition.MySQLCondition;
import org.wj.prajumsook.condition.democondition.service.MongoDBUserService;
import org.wj.prajumsook.condition.democondition.service.MySQLUserService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Conditional(MySQLCondition.class)
    public MySQLUserService mySQLUserService() {
        return new MySQLUserService();
    }

    @Bean
    @Conditional(MongoDBCondition.class)
    public MongoDBUserService mongoDBUserService() {
        return new MongoDBUserService();
    }
}

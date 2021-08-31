package com.biniam;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.biniam.service", "com.biniam.util"})
public class SpringBootWithDynamoDBTestConfiguration {

    /*
    @Bean
    public DynamoTableService dynamoTableService() {
        return new DynamoTableService();
    }
    */
}

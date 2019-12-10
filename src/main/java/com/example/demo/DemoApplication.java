package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author GEEX177
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public Registry registry(ApplicationContext applicationContext) {
//        return new Registry(applicationContext);
//    }
//
//    @Bean
//    public Bus commandBus(Registry registry) {
//        return new SpringBus(registry);
//    }

}

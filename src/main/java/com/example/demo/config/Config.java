package com.example.demo.config;

import com.example.demo.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author GEEX177
 * @date 2019/10/8
 */
@Configuration
public class Config {

    @Bean
//    @Scope("prototype")  多例
    @Lazy
    public Person person() {
        System.out.println("already load 'person'");
        return new Person("Tim", 12);
    }
}

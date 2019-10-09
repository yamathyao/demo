package com.example.demo;

import com.example.demo.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author GEEX177
 * @date 2019/10/8
 */
public class Test {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext annoApp = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("has not get 'person'");
        Object p1 = annoApp.getBean("person");
        Object p2 = annoApp.getBean("person");
        System.out.println(p1 == p2);
    }
}

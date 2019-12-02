package com.example.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @author GEEX177
 * @date 2019/10/8
 */
public class Test {

    public static void main(String[] args) throws Exception {

//        AnnotationConfigApplicationContext annoApp = new AnnotationConfigApplicationContext(Config.class);
//        System.out.println("has not get 'person'");
//        Object p1 = annoApp.getBean("person");
//        Object p2 = annoApp.getBean("person");
//        System.out.println(p1 == p2);

        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();

        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
        System.out.println("Total : " + bill);
    }
}

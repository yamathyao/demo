package com.example.demo;

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

//        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
//
//        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
//        System.out.println("Total : " + bill);

        method(null);
    }

    public static void method(String param) {
        switch (param) {
// 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
// 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
// 也不是进入这里
            default:
                System.out.println("default");
        }
    }
}

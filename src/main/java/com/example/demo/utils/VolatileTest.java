package com.example.demo.utils;

/**
 * @author GEEX177
 * @date 2019/10/30
 */
public class VolatileTest {

    private volatile static VolatileTest instance;

    public VolatileTest() {
        System.out.println("= = =");
    }

    public static VolatileTest volatileTest() {
        if (instance == null) {
            synchronized (VolatileTest.class) {
                if (instance == null) {
                    instance = new VolatileTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    volatileTest();
                }
            }).start();
        }
    }
}

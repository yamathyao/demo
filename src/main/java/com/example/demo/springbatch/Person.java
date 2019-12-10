package com.example.demo.springbatch;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yao
 * @date 2019/12/10
 */

public class Person {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    public Person() {}

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

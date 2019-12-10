package com.example.demo.springbatch;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author yao
 * @date 2019/12/10
 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, String> {

    @Override
    public String process(Person person) throws Exception {
        String greeting = "Hello " + person.toString() + "!";
        log.info("convert '{}' into '{}'", person, greeting);
        return greeting;
    }
}

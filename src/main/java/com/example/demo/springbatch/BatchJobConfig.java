package com.example.demo.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * @author yao
 * @date 2019/12/10
 */

@Configuration
public class BatchJobConfig {

    @Bean
    public Job batchJob(JobBuilderFactory jobBuilder, StepBuilderFactory stepBuilder) {
        return jobBuilder.get("Batch Job").start(batchStep(stepBuilder)).build();
    }

    @Bean
    public Step batchStep(StepBuilderFactory stepBuilder) {
        return stepBuilder.get("Batch Step").<Person, String>chunk(10).reader(reader())
                .processor(processor()).writer(writer()).build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>().name("personItemReader").resource(new ClassPathResource("csv/person.csv"))
                .delimited().names(new String[]{"firstName", "lastName"}).targetType(Person.class).build();
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>().name("greetingItemWriter").resource(new FileSystemResource("target/test-outputs/greetings.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }
}

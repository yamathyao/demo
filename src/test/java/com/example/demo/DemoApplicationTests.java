package com.example.demo;

import com.example.demo.commands.CreateOrderCommand;
import com.example.demo.cqs.Bus;
import com.example.demo.model.OrderInfoModel;
import com.example.demo.service.DemoService;
import com.example.demo.sort.*;
import com.example.demo.springbatch.BatchConfig;
import com.example.demo.springbatch.BatchJobConfig;
import com.example.demo.utils.IntArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoApplication.class, DemoApplicationTests.BatchTestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DemoApplicationTests {

    @Autowired
    private DemoService demoService;

    @Autowired
    private Bus bus;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testBubbleSort() {
        int[] nums = IntArrayUtil.initIntArrays(7, 9);
        log.info("排序前：" + Arrays.toString(nums));
        BubbleSort.bubbleSort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testHeapSort() {
        int[] nums = IntArrayUtil.initIntArrays(7, 9);
        log.info("排序前：" + Arrays.toString(nums));
        HeapSort.sort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testInsertSort() {
        int[] nums = IntArrayUtil.initIntArrays(10, 100);
        log.info("排序前：" + Arrays.toString(nums));
        InsertSort.sort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testMergeSort() {
        int[] nums = IntArrayUtil.initIntArrays(9, 100);
        log.info("排序前：" + Arrays.toString(nums));
        MergeSort.sort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testQuickSort() {
        int[] nums = IntArrayUtil.initIntArrays(10, 100);
        log.info("排序前：" + Arrays.toString(nums));
        QuickSort.sort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testRadixSort() {
        int[] nums = IntArrayUtil.initIntArrays(20, 100000);
        log.info("排序前：" + Arrays.toString(nums));
        RadixSort.sort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testSelectSort() {
        int[] nums = IntArrayUtil.initIntArrays(10, 100);
        log.info("排序前：" + Arrays.toString(nums));
        SelectSort.sort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testShellSort() {
        int[] nums = IntArrayUtil.initIntArrays(9, 100);
        log.info("排序前：" + Arrays.toString(nums));
        ShellSort.sort(nums);
        log.info("排序后：" + Arrays.toString(nums));
    }

    @Test
    public void testAop() {
        demoService.goTest(new String[]{"1", "2"});
    }

    @Test
    public void testBusCommand() {
        CreateOrderCommand command = new CreateOrderCommand();
        command.setOrderId("123456");
        command.setProductName("苹果");
        command.setStatus("已发货");
        OrderInfoModel orderInfoModel = bus.executeCommand(command);
        Assert.assertNotNull(orderInfoModel);
        System.out.println(orderInfoModel);
    }

    @Test
    public void test() {
        demoService.demo资金方开户();
    }

//    Spring batch

    @Test
    public void testSpringBatch() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getExitStatus().getExitCode(), is("COMPLETED"));
    }

    @Configuration
    @Import({BatchConfig.class, BatchJobConfig.class})
    static class BatchTestConfig {
        @Autowired
        private Job batchJob;

        @Bean
        JobLauncherTestUtils jobLauncherTestUtils() throws NoSuchJobException {
            JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
            jobLauncherTestUtils.setJob(batchJob);
            return jobLauncherTestUtils;
        }
    }
}

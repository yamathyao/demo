package com.example.demo;

import com.example.demo.sort.*;
import com.example.demo.utils.IntArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DemoApplicationTests {

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
}

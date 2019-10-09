package com.example.demo.utils;

import java.util.Random;

/**
 * @author GEEX177
 * @date 2019/10/9
 */
public class IntArrayUtil {

    /**
     * 初始化int数组
     *
     * @param length 数组长度
     * @param maxNum 数组中元素最大值
     * @return
     */
    public static int[] initIntArrays(int length, int maxNum) {
        if (length <= 0 || maxNum <= 0) {
            throw new RuntimeException("wrong int array");
        }
        int[] nums = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(maxNum);
        }
        return nums;
    }

    /**
     * 交换
     *
     * @param nums 数组
     * @param i    数组下标i
     * @param j    数组下标j
     */
    public static void exchangeValue(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}

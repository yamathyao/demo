package com.example.demo.sort;

import com.example.demo.utils.IntArrayUtil;

/**
 * 简单选择排序
 * <p>
 * 从待排序中找出值最小的元素，如果最小元素不是第一个元素，则将其与第一个元素交换。
 * 然后剩下的n-1个元素中找出最小的，重复操作直到排序结束。
 *
 * @author GEEX177
 * @date 2019/10/10
 */
public class SelectSort {

    public static void sort(int[] nums) {
        int i, j;
        for (i = 0; i < nums.length; i++) {
            int minValueIndex = i;
            // 剩余n-1个元素找出最小的
            for (j = i; j < nums.length; j++) {
                if (nums[minValueIndex] > nums[j]) {
                    minValueIndex = j;
                }
            }
            IntArrayUtil.exchangeValue(nums, i, minValueIndex);
        }
    }
}

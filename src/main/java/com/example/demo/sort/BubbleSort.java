package com.example.demo.sort;

import com.example.demo.utils.IntArrayUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡
 * <p>
 * 从左往右相邻的两个元素比较，如果左元素大于右元素，交换左右元素。
 * 即每次比较完，最大元素总能到最右边。
 * 当所有轮次比较完后，就是从小到大排序。
 *
 * @author GEEX177
 * @date 2019/10/9
 */
@Slf4j
public class BubbleSort {

    public static void bubbleSort(int[] nums) {
        // 比较次数
        for (int i = 0; i < nums.length; i++) {
            // 当前比较的次数
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    IntArrayUtil.exchangeValue(nums, j, j + 1);
                }
            }
        }
    }
}

package com.example.demo.sort;

/**
 * 直接插入
 * <p>
 * 将n待排序的元素分为一个有序表和一个无序表。开始时有序表只包含第一个元素，无序表包含n-1个元素。
 * 排序过程中每次从无序表中取出第一个元素，并直接插入到有序表中合适的位置，重复n-1次后完毕。
 *
 * @author GEEX177
 * @date 2019/10/9
 */
public class InsertSort {

    public static void sort(int[] nums) {
        // i是无序表下标，temp是待插入元素
        int i, temp;
        for (i = 1; i < nums.length; i++) {
            temp = nums[i];
            // 待插元素应该在的位置
            int k;
            // 比待插元素大的往右移
            for (k = i; k > 0 && nums[k - 1] > temp; k--) {
                nums[k] = nums[k - 1];
            }
            nums[k] = temp;
        }
    }
}

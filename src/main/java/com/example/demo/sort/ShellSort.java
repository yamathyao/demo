package com.example.demo.sort;

/**
 * 希尔排序
 * <p>
 * 先将整个序列分割成若干个子序列（按相隔某个“增量”的下标），并对这些子序列进行直接插入排序。
 * 然后依次缩减这个“增量”，分割成子序列并再次进行插入排序。
 * 重复上述过程到“增量”为1，即对整个待排序列进行了依次直接插入排序。
 *
 * @author GEEX177
 * @date 2019/10/10
 */
public class ShellSort {

    public static void sort(int[] nums) {
        // i为无序表元素下标，j为有序表元素下标，temp为待插入元素，increment为增量
        int i, j, temp, increment;
        for (increment = nums.length / 2; increment >= 1; increment = increment / 2) {
            for (i = increment; i < nums.length; i++) {
                temp = nums[i];
                j = i - increment;
                for (; j >= 0 && temp < nums[j]; j -= increment) {
                    nums[j + increment] = nums[j];
                }
                nums[j + increment] = temp;
            }
        }
    }
}

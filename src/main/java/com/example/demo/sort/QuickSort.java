package com.example.demo.sort;

import com.example.demo.utils.IntArrayUtil;

/**
 * 快速排序
 * <p>
 * 首先在待排序列中随机选一个基准数（代码实现默认第一个元素），然后把待排序列中比基准数小的都放在其左边，
 * 比基准数大的放在其右边。然后把该基准数的左右两边看做两个待排序列，重复上述操作，直至所有基准数放在正确的位置，排序完毕。
 *
 * @author GEEX177
 * @date 2019/10/9
 */
public class QuickSort {

    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 对下标从start到end的数组快速排序
     *
     * @param nums  待排数组
     * @param start 数组起始下标
     * @param end   数组截止下标
     */
    private static void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        // 数组起始下标的元素，作为基准数
        int base = nums[start];
        // left看作左哨兵，right看作右哨兵
        int left = start;
        int right = end;
        // 左哨兵遇到由哨兵
        while (left != right) {
            // 特别注意！！！ 选取基准数是左哨兵位置，所以右哨兵先走。
            // 循环结束条件是左右哨兵相遇，如果左哨兵先走，出现左哨兵找到右哨兵结束循环，
            // 左右哨兵的当前元素是基于右哨兵上次循环结束的位置。
            // 即此时左右哨兵相遇的位置的当前元素是比基准数大的，将该元素与基准数互换是不正确的。
            // 右哨兵找比基准数小的元素
            while (left < right && nums[right] >= base) {
                right--;
            }
            // 左哨兵找比基准数大的元素
            while (left < right && nums[left] <= base) {
                left++;
            }
            //
            if (left < right) {
                // 交换，保证左边的比基准数小，右边的比基准数大
                IntArrayUtil.exchangeValue(nums, left, right);
            }
            //
        }
        // 交换，保证基准数在正确的位置
        IntArrayUtil.exchangeValue(nums, start, right);
        // 基准数左边
        quickSort(nums, start, right - 1);
        // 基准数右边
        quickSort(nums, right + 1, end);
    }
}

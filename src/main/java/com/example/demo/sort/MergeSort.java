package com.example.demo.sort;

/**
 * 归并
 * 分治策略，分成若干个子序列，再子序列排序，然后合并成最终序列
 * <p>
 * 比较两个子数组中“哨兵”对应的下标，将值较小的放在临时数组对应的位置，同时值较小的“哨兵”右移，
 * 重复操作，直到哨兵巡逻完对应子数组的所有元素。
 * 再将临时数组copy到原数组中，归并完成。
 *
 * @author GEEX177
 * @date 2019/10/9
 */
public class MergeSort {

    public static void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    /**
     * 归并算法的实现
     *
     * @param nums  待排序列
     * @param start 数组起始下标
     * @param end   数组截止下标
     * @param temp  临时数组
     */
    private static void mergeSort(int[] nums, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid, temp);
            mergeSort(nums, mid + 1, end, temp);
            merge(nums, start, mid, end, temp);
        }
    }

    /**
     * 小数组合并成大数组
     *
     * @param nums  待排序列
     * @param start 数组起始下标
     * @param mid   中间下标
     * @param end   数组截止下标
     * @param temp  临时数组
     */
    private static void merge(int[] nums, int start, int mid, int end, int[] temp) {
        // 定义两个值i、j（i在数组[start...mid]移动，j在数组[mid + 1...end]移动）
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (nums[i] > nums[j]) {
                temp[k++] = nums[j++];
            } else {
                temp[k++] = nums[i++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= end) {
            temp[k++] = nums[j++];
        }
        // 临时数组copy到原数组
        for (k = 0; start <= end; start++, k++) {
            nums[start] = temp[k];
        }
    }
}

package com.example.demo.linkedlist;

import java.util.Arrays;

/**
 * 排列
 *
 * @author yao
 * @date 2020/1/3
 */

public class PermutationSolution {

    /**
     * 递归
     *
     * @param arr
     * @param k
     */
    public static void permutation(int[] arr, int k) {
        if (k == arr.length - 1) {
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = k; i < arr.length; i++) {
                // 将 k 与 之后元素 i 依次交换，可以认为选中了第 k 位
                swap(arr, k, i);
                // 第 k 位选择完成后，求剩余元素的全排列
                permutation(arr, k + 1);
                // 这一步很关键：将 k 与 i 换回来，保证是初始的顺序
                swap(arr, k, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 字典序法，如果有下一个全排列数，返回true，否则返回false
     *
     * @param arr
     */
    public static boolean nextPermutation(int[] arr) {
        // 记录从右到左第一个左邻小于右邻的数对应的索引
        int beforeIndex = 0;
        int currentIndex;
        // 是否存在从右到左第一个左邻小于右邻的数对应的索引
        boolean isAllReverse = true;
        // 1.从右到左寻找第一个左邻小于右邻的数
        for (currentIndex = arr.length - 1; currentIndex > 0; --currentIndex) {
            beforeIndex = currentIndex - 1;
            if (arr[beforeIndex] < arr[currentIndex]) {
                isAllReverse = false;
                break;
            }
        }
        // 如果不存在，说明这个数已经是字典序法里最大值，
        if (isAllReverse) {
            return false;
        } else {
            // 2.再从右往左找第一个比第一步更大的数
            int firstLargeIndex = 0;
            for (firstLargeIndex = arr.length - 1; firstLargeIndex > beforeIndex; --firstLargeIndex) {
                if (arr[firstLargeIndex] > arr[beforeIndex]) {
                    break;
                }
            }
            // 3. 交换方法1,2得出的两个数
            swap(arr, beforeIndex, firstLargeIndex);
            // 4. 排序
            Arrays.sort(arr, beforeIndex + 1, arr.length);
            return true;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        // 递归
//        permutation(arr, 0);
        // 字典排序
        while (nextPermutation(arr)) {
            System.out.println(Arrays.toString(arr));
        }
    }
}

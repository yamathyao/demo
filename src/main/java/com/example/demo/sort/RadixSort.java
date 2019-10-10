package com.example.demo.sort;

/**
 * 基数排序/桶排序
 * <p>
 * 依次从低位到高位分解待排序列的元素并排序，当按最高位分解并排序后，排序完毕
 *
 * @author GEEX177
 * @date 2019/10/10
 */
public class RadixSort {

    public static void sort(int[] nums) {
        // 最大位数
        int maxBit = getMaxBit(nums);
        // 临时二维数组，列是余数（0-9），行是0或者序列中元素值
        int[][] temp = new int[10][nums.length];
        // 下标，待排序列元素对应余数的个数
        int[] order = new int[10];
        // 个位 十位 ...
        int n = 1;
        // 位数
        int m = 1;
        int k = 0;

        while (m <= maxBit) {
            for (int num : nums) {
                // 取余
                int remainder = (num / n) % 10;
                temp[remainder][order[remainder]] = num;
                order[remainder]++;
            }
            // 重新排序
            for (int i = 0; i < order.length; i++) {
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        nums[k] = temp[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            // 重置变量
            n *= 10;
            k = 0;
            m++;
        }
    }

    private static int getMaxBit(int[] nums) {
        // 初始化
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int maxBit;
        for (maxBit = 1; max >= 10; maxBit++) {
            max = max / 10;
        }
        return maxBit;
    }
}

package com.example.demo.sort;

import com.example.demo.utils.IntArrayUtil;

/**
 * 堆
 * <p>
 * 堆是一颗顺序存储的二叉树。每个节点的值都不大于其子节点的值，称之为“小根堆”，反之称之为“大根堆”。
 * “大根堆”的堆顶元素就是这个堆的最大值。利用将无序序列初始化为“大根堆”，取出其堆顶元素作为最大值，
 * 然后将剩余的元素看做无序序列，并再次变为“大根堆”并取出其堆顶元素。重复迭代直到无序序列只有一个元素即排序完毕。
 *
 * @author GEEX177
 * @date 2019/10/9
 */
public class HeapSort {

    /**
     * 排序
     */
    public static void sort(int[] nums) {
        // 初始化大根堆
        initBigHeap(nums);
        //
        for (int i = nums.length; i >= 1; i--) {
            // 堆顶元素为最大值
            IntArrayUtil.exchangeValue(nums, 0, i - 1);
            // 重新调整，前i- 1个元素符合大根堆
            adjustBigHeap(nums, 0, i - 1);
        }
    }

    /**
     * 初始化大根堆
     */
    private static void initBigHeap(int[] nums) {
        // 从最后一个有子节点的父节点开始遍历，子节点都不大于父节点，遍历到堆顶。
        // 第i个元素的下标i-1，父节点 i/2 - 1
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustBigHeap(nums, i, nums.length);
        }
    }

    /**
     * 符合大根堆
     * 保证数组nums的前count个元素中，下标为parentIndex的节点及其子节点都符合大根堆
     *
     * @param nums        待排数组
     * @param parentIndex 目标节点
     * @param count       待排元素个数（nums前count个元素）
     */
    private static void adjustBigHeap(int[] nums, int parentIndex, int count) {
        // 左子节点
        int leftChildIndex = 2 * parentIndex + 1;
        // 右子节点
        int rightChildIndex = 2 * parentIndex + 2;
        // 有节点
        while (leftChildIndex < count) {
            int toBeComparedIndex;
            // 如果有右子节点，并且右子节点比左子节点大，跟右子节点比较
            if (rightChildIndex < count && nums[rightChildIndex] > nums[leftChildIndex]) {
                toBeComparedIndex = rightChildIndex;
            }
            // 否则，跟左子节点比较
            else {
                toBeComparedIndex = leftChildIndex;
            }
            // 比目标节点大，交换
            if (nums[toBeComparedIndex] > nums[parentIndex]) {
                IntArrayUtil.exchangeValue(nums, parentIndex, toBeComparedIndex);
                // 交换后，还要保证大根堆要求
                adjustBigHeap(nums, toBeComparedIndex, count);
            } else {
                // 父节点最大，停止递归
                break;
            }
        }

    }
}

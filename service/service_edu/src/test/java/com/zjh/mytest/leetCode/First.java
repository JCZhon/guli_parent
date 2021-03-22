package com.zjh.mytest.leetCode;

import java.util.Arrays;

/**
 * 第1题：
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。
 */
public class First {
    public int[] twoSum(int[] nums, int target) {

        int[] result = null;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result = new int[]{i, j};
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        First first = new First();
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(first.twoSum(nums, 9)));
    }
}

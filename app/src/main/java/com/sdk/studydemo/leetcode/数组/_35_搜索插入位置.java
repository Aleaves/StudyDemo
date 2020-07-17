package com.sdk.studydemo.leetcode.数组;

public class _35_搜索插入位置 {

    public int searchInsert(int[] nums, int target) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                return i;
            } else if (target < nums[i]) {
                return index;
            } else {
                index++;
            }
        }
        return index;
    }

    

}

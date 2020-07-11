package com.sdk.studydemo.leetcode.数组;

public class _27_移除元素 {

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

}

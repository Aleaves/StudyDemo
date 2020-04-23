package com.sdk.studydemo;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
//        ArrayTest arrayTest = new ArrayTest();
//        boolean isAnagram = arrayTest.isAnagram1("anagram", "nagaram");
//        System.out.println(isAnagram);

//        int[] nums = new int[]{2, 7, 11, 15};
//        int[] results = twoSum2(nums, 13);
//        for (int result : results) {
//            System.out.println(result);
//        }

//        plus();

        //System.out.println(reverse1(-12880));
        //System.out.println(isPalindrome(12321));
        //System.out.println(romanToInt("LVIII"));
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }


    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == target - nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void plus() {
        int num = 0;
        for (int i = 1; i <= 365; i++) {
            num = num + i;
        }
        System.out.println("从1-365之和=" + num);
    }

    public static int reverse(int x) {
        String xString = Integer.toString(x);
        String string = xString;
        int flag = 1;
        if (x < 0) {
            flag = -1;
            string = xString.substring(1);
        }
        try {
            return Integer.parseInt(new StringBuilder(string).reverse().toString()) * flag;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int reverse1(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int n = 0;
        int nx = x;
        while (nx != 0) {
            n = n * 10 + nx % 10;
            nx = nx / 10;
        }
        return x == n;
    }

    public static int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }


    private static int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = getCommonPrefix(prefix,strs[i]);
            if("".equals(prefix)){
                return "";
            }
        }
        return prefix;
    }

    public static String getCommonPrefix(String s1, String s2) {
        int size = s1.length() < s2.length() ? s1.length() : s2.length();
        int preIndex = 0;
        for (; preIndex < size; preIndex++) {
            if(s1.charAt(preIndex) != s2.charAt(preIndex)){
                break;
            }
        }
        return s1.substring(0,preIndex);
    }


}

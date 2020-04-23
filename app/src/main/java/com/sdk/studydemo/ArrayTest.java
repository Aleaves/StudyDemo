package com.sdk.studydemo;

import java.util.Arrays;

public class ArrayTest {
    /**
     * 有效的字母异位词
     */
    public boolean isAnagram1(String s, String t) {
        //先判断长度是否相等  不相等直接返回false
        if (s.length() != t.length()) {
            return false;
        }

        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        //将字母数组排序
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }



}

package com.sdk.studydemo;

public class Test {
    public static void main(String[] args) {
        isAnagram("anagram", "nagaram");
    }


    public static boolean isAnagram(String s, String t) {
        char[] chars = new char[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }

        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }

        for (char c : t.toCharArray()) {
            chars[c - 'a']--;
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }

        return true;
    }

}
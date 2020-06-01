package com.sdk.kotdemo.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TestSum {
    public static void main(String[] args) {
        int[] a = twoSum(new int[]{2, 7, 11, 15}, 9);
        //int[] a = twoSum1(new int[]{2,7,11,15},9);
//        for (int i : a) {
//            System.out.println(i);
//        }

        //System.out.println(romanToInt("XII"));
//        System.out.println(longestCommonPrefix(new String[]{
//                "flower", "flow", "flight"
//        }));
        System.out.println(isValid("([)]"));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                System.out.println(nums[i] + "==" + nums[j]);
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no two sum solution");
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> maps = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int compoment = target - nums[i];
            if (maps.containsKey(compoment)) {
                return new int[]{i, maps.get(compoment)};
            }
            maps.put(nums[i], i);
        }
        throw new IllegalArgumentException("no two sum solution");
    }

    public static int romanToInt(String s) {
        int sum = 0;
        char[] romas = s.toCharArray();
        int preNum = getValue(romas[0]);
        for (int i = 1; i < romas.length; i++) {
            int num = getValue(romas[i]);
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
            prefix = getCommonPrefix(prefix, strs[i]);
            if ("".equals(prefix)) {
                return "";
            }
        }
        return prefix;
    }

    public static String getCommonPrefix(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int length = Math.min(chars1.length, chars2.length);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (chars1[i] == chars2[i]) {
                stringBuilder.append(chars1[i]);
            }
        }
        return stringBuilder.toString();
    }


    public static boolean isValid(String s) {
        if ("".equals(s)) {
            return false;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        if (isMatch(chars[0], chars[1])) {
            for (int i = 0; i < chars.length; i += 2) {
                if (!isMatch(chars[i], chars[i + 1])) {
                    return false;
                }
            }
            return true;
        } else {
            int half = chars.length / 2;
            int j = 1;
            for (int i = half; i < chars.length; i++) {
                if (!isMatch(chars[half - j], chars[i])) {
                    return false;
                }
                j++;
            }
            return true;
        }
    }

    public static boolean isMatch(char s1, char s2) {
        if ('(' == s1) {
            if (')' == s2) {
                return true;
            } else {
                return false;
            }
        } else if ('{' == s1) {
            if ('}' == s2) {
                return true;
            } else {
                return false;
            }
        } else {
            if (']' == s2) {
                return true;
            } else {
                return false;
            }
        }

    }


    public static boolean isValid1(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{
            add('?');
        }};
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) stack.addLast(c);
            else if (map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }


    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};

    public static boolean isValid2(String s) {

        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;

        LinkedList<Character> lists = new LinkedList<Character>() {
            {
                add('?');
            }
        };

        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (map.containsKey(c)) {
                lists.add(c);
            } else if (map.get(lists.removeLast()) != c) {
                return false;
            }
        }
        return lists.size() == 1;
    }

}

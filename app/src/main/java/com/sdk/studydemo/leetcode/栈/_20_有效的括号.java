package com.sdk.studydemo.leetcode.栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _20_有效的括号 {

    private static Map<Character, Character> maps = new HashMap<>();

    {
        maps.put('(', ')');
        maps.put('{', '}');
        maps.put('[', ']');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if(maps.containsKey(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;

                if(c != maps.get(stack.pop())) return false;
            }
        }

        return stack.isEmpty();

    }

}

package com.sdk.studydemo.shuju.arry;

import java.util.LinkedList;

public class ArrayTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(0,10);
        list.add(5,11);

        list.remove(11);
        System.out.println(list.toString());

    }
}

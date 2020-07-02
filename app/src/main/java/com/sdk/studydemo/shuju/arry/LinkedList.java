package com.sdk.studydemo.shuju.arry;

public class LinkedList<E> {

    private int size;
    private Node<E> firstNode;

    private static class Node<E> {
        E element;
        Node<E> next;
    }

}

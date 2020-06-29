package com.sdk.studydemo;

public class Test01 {

    public class Node {
        int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node reverseKNode(Node head, int k) {
        if (k < 2) {
            return head;
        }

        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;
        int count = 1;

        while (cur != null){
            next = cur.next;

        }
        return head;
    }

}

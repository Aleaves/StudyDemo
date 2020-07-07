package com.sdk.studydemo.leetcode.链表;

import java.util.List;

public class _876_链表的中间结点 {

    public ListNode middleNode(ListNode head) {
        int size = 1;
        ListNode node = head;
        while (node != null && node.next != null) {
            size++;
            node = node.next;
        }
        int half = size / 2 + 1;
        for (int i =1 ;i<=half;i++){
            head = head.next;
        }

        return head;

    }

    /**
     * 快慢指针
     * 用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。那么当 fast 到达链表的末尾时，slow 必然位于中间。
     * @param head
     * @return
     */
    public ListNode middleNode1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}

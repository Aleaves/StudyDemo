package com.sdk.studydemo.leetcode.链表;

public class _203_移除链表元素 {

    public ListNode removeElements(ListNode head, int val) {
        //初始化哨兵节点
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode cur = head;
        while (cur != null){
            if(cur.val == val){
                prev.next = cur.next;
            }else{
                prev = cur;
            }
            cur = cur.next;
        }
        return sentinel.next;
    }


}

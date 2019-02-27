package com.divashchenko;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {


    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);


        System.out.println("Before: " + listNodeListToString(head));
        System.out.println();

        head = deleteDuplicates(head);

        System.out.println("After: " + listNodeListToString(head));
    }

    private static ListNode deleteDuplicates(ListNode head) {
        Deque<ListNode> queue = new ArrayDeque<>();

        if (head == null) {
            return null;
        } else {
            queue.add(head);
        }

        while (head != null) {
            if (queue.getLast().val != head.val) {
                queue.getLast().next = head;
                queue.add(head);
            }
            head = head.next;
        }
        queue.getLast().next = null;

        return queue.getFirst();
    }

    private static String listNodeListToString(ListNode head) {
        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append(head.val);
            sb.append("->");
            head = head.next;
        }

        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.toString();
    }
}

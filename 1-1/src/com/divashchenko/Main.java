package com.divashchenko;

import java.util.*;

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
        HashSet<ListNode> hashSet = new HashSet<>();

        while (head != null) {
            hashSet.add(head);
            head = head.next;
        }

        List<ListNode> tmpList = new ArrayList<>(hashSet);
        for (int i = 0; i < tmpList.size() - 1; i++) {
            tmpList.get(i).next = tmpList.get(i + 1);
        }
        tmpList.get(tmpList.size() - 1).next = null;

        return tmpList.get(0);
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

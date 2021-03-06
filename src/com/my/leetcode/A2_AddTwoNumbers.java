package com.my.leetcode;

import com.my.util.OutUtil;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2021/7/12
 */
public class A2_AddTwoNumbers {
    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    /**官方解法 使用while循环（循环次数：两个链表最长长度链）
    链表分为head+tail，其中head为头，tail为当前链表指向。tail.next是当前链表指向的下一个
        思路：
         * 1.循环链表，范围两个都循环完毕
         * 2.算出两数之和+上次进位=sum
         * 3.1如果是结果链表head，sum%10（结果个位数）放在head，移动尾巴指向tail
         * 3.2非head值，sum%10赋值新next，移动尾巴指向next
         * 4.移动两个链表
         * 5.进位sum/10赋值tail.next
     **/
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = null, tail = null;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int n1 = l1 != null ? l1.val : 0;
                int n2 = l2 != null ? l2.val : 0;
                int sum = n1 + n2 + carry;
                if (head == null) {
                    head = tail = new ListNode(sum % 10);
                } else {
                    tail.next = new ListNode(sum % 10);
                    tail = tail.next;
                }
                carry = sum / 10;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (carry > 0) {
                tail.next = new ListNode(carry);
            }
            return head;
        }
    }

    //递归方法，自己解答
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newListNode = new ListNode();
        //递归出口
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            l1 = new ListNode(0);
        } else if (l2 == null) {
            l2 = new ListNode(0);
        }
        //1.相加
        int sumRes = l1.val + l2.val;
        //2.进位，存进位到l1的next的val里
        if (sumRes >= 10) {
            sumRes = sumRes - 10;
            if (l1.next != null) {
                l1.next.val += 1;
            } else {
                l1.next = new ListNode(1);
            }
        }
        //3.当前node.val存结果
        newListNode.val += sumRes;
        //4.递归
        newListNode.next = addTwoNumbers(l1.next, l2.next);
        return newListNode;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(List<Integer> inList) {
            if(inList == null && inList.isEmpty()){
                return;
            }
            this.val = inList.get(0);
            if(inList.size()>1){
                inList.remove(0);
                this.next = new ListNode(inList);
            }
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append( "\n{ListNode{val=").append(val).append(",next=");
            if(this.next!=null){
                sb.append(next.toString());
            }else {
                sb.append("{}");
            }
            sb.append("}");

            return sb.toString();
        }
    }


    public void ss() {
        List<Integer> numList1 = new LinkedList(Arrays.asList(new Integer[]{9,9,9,9,9,9,9}));
        List<Integer> numList2 = new LinkedList(Arrays.asList(new Integer[]{9,9,9,9}));
        ListNode l1 = new ListNode(numList1);
        ListNode l2 = new ListNode(numList2);
        ListNode node = addTwoNumbers(l1, l2);
        System.out.println(node.toString());
    }

    public static void main(String[] args) {
        new A2_AddTwoNumbers().ss();
    }
}

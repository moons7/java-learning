package leetcode;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class Solution2 {
    //复用链表L1空间进行递归计算
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean hasNext = true;
        if (l1.next == null && l2.next == null) {
            hasNext = false;
        }
        if (hasNext) {
            if (l1.next == null) {
                l1.next = new ListNode(0);
            } else if (l2.next == null) {
                l2.next = new ListNode(0);
            }
        }
        int result = l1.val + l2.val;
        if (result > 9) {
            if (l1.next == null) {
                l1.next = new ListNode(0);
            }
            l1.val = result - 10;
            ++l1.next.val;
        } else {
            l1.val = result;
        }
        if (hasNext) {
            addTwoNumbers(l1.next, l2.next);
        }
        return l1;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

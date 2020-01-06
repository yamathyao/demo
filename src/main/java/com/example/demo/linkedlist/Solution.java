package com.example.demo.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表
 *
 * @author yao
 * @date 2020/1/2
 */

public class Solution {

    public static class Node {
        /**
         * 节点的对象
         */
        public int data;
        /**
         * 节点的引用，指向下一个节点,注意：属性不能定义为public,这里只是为了演示方便,生产中还是要定义成 private!
         */
        public Node next = null;

        public Node(int val) {
            data = val;
        }
    }

    public static class LinkedList {
        /**
         * 链表长度，非必须，可不加
         */
        int length = 0;
        /**
         * 哨兵节点
         */
        Node head = new Node(0);

        /**
         * 尾插法
         *
         * @param val
         */
        public void addNode(int val) {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node(val);
        }

        /**
         * 头插法
         */
        public void headInsert(int val) {
            // 构造新节点
            Node newNode = new Node(val);
            // 新节点指向头结点之后的节点
            newNode.next = head.next;
            // 头结点指向新节点
            head.next = newNode;
        }

        /**
         * 删除指定结点
         */
        public void removeSelectedNode(Node deletedNode) {
            // 如果结点是尾结点，还是需要从头遍历到尾结点的前继节点，再将尾结点删除
            if (deletedNode.next == null) {
                Node tmp = head;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                // 找到尾结点，删除
                tmp.next = null;
            } else {
                Node nextNode = deletedNode.next;
                // 将删除结点的后继结点值赋给被删除结点
                deletedNode.data = nextNode.data;
                // 将 nextNode 删除
                deletedNode.next = nextNode.next;
                nextNode.next = null;
            }
        }

        /**
         * 递归翻转结点 node 开始的链表
         */
        public Node invertLinkedList(Node node) {
            if (node.next == null) {
                return node;
            }
            // 1. 先翻转 node 之后的链表
            Node newHead = invertLinkedList(node.next);
            // 2. 再把 node 结点后继结点的后继结点(3)指向 node， node 的后继结点为空
            node.next.next = node;
            node.next = null;
            // 3. 返回翻转后的头结点
            return newHead;
        }

        /**
         * 迭代翻转
         */
        public void iterationInvertLinkedList() {
            // 1.
            Node pre = head.next;
            Node cur = pre.next;
            pre.next = null;

            while (cur != null) {
                /*
                 * 务必注意：在 cur 指向 pre 之前一定要先保留 cur 的后继结点，不然 cur 指向 pre 后就再也找不到后继结点了
                 * 也就无法对 cur 后继之后的结点进行翻转了
                 */
                Node next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // 此时 pre 为头结点的后继结点
            head.next = pre;
        }

        /**
         * 迭代翻转 from 到 to 的结点
         */
        public void iterationInvertLinkedList(int fromIndex, int toIndex) throws Exception {
            // 1
            // from - 1 结点
            Node fromPre = null;
            // from 结点
            Node from = null;
            // to 结点
            Node to = null;
            // to + 1 结点
            Node toNext = null;

            Node tmp = head.next;
            // 头结点的 index 为 1
            int curIndex = 1;

            while (tmp != null) {
                if (curIndex == fromIndex - 1) {
                    fromPre = tmp;
                } else if (curIndex == fromIndex) {
                    from = tmp;
                } else if (curIndex == toIndex) {
                    to = tmp;
                } else if (curIndex == toIndex + 1) {
                    toNext = tmp;
                }
                tmp = tmp.next;
                curIndex++;
            }

            if (from == null || to == null) {
                // from 和 to 都超过尾结点不翻转
                throw new Exception("不符合条件");
            }
            // 使用循环迭代法翻转从 from 到 to 的结点
            Node pre = from;
            Node cur = pre.next;
            while (cur != toNext) {
                Node next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // 将 from-1 节点指向 to 结点（如果从 head 的后继结点开始翻转，则需要重新设置 head 的后继结点），将 from 结点指向 to + 1 结点
            if (fromPre != null) {
                fromPre.next = to;
            } else {
                // 如果 fromPre 为 null, 说明从 head 的后继结点开始翻转
                head.next = to;
            }
            from.next = toNext;
        }

        /**
         * 顺序每k个一组翻转链表
         */
        public void iteratorInvertLinkedListEveryK(int k) {
            Node tmp = head.next;
            // 计数 用来找出首结点和末结点
            int step = 0;
            // k个一组链表中的头结点
            Node startK = null;
            // k个一组这段链表头结点的前置结点
            Node startKPre = head;
            // k个一组链表中的尾结点
            Node endK;

            while (tmp != null) {
                // 提前保存 tmp 的下一个结点，因为由于翻转，tmp 的后续结点会变
                Node tmpNext = tmp.next;
                if (step == 0) {
                    // k个一组链表的头结点
                    startK = tmp;
                    step++;
                } else if (step == k - 1) {
                    // 此时找到了k个一组链表区间的尾结点( endK )，对这段链表用迭代翻转
                    endK = tmp;
                    Node pre = startK;
                    Node cur = startK.next;
                    if (cur == null) {
                        break;
                    }
                    Node endNext = endK.next;
                    while (cur != endNext) {
                        Node next = cur.next;
                        cur.next = pre;
                        pre = cur;
                        cur = next;
                    }
                    // 翻转后此时 endK 和 startK 分别是 k个一组链表中的首尾结点
                    startKPre.next = endK;
                    startK.next = endNext;
                    // 当前的 k个一组链表翻转完成，开始下一组 k个一组的翻转
                    startKPre = startK;
                    step = 0;
                } else {
                    step++;
                }
                tmp = tmpNext;
            }
        }

        /**
         * 逆序每 k 个一组链表翻转
         */
        public void reverseIterationInvertLinkedListEveryK(int k) {
            // 先翻转链表
            iterationInvertLinkedList();
            // k 个一组翻转
            iteratorInvertLinkedListEveryK(k);
            // 再翻转链表
            iterationInvertLinkedList();
        }

        /**
         * 打印链表
         */
        public void printList() {
            Node tmp = head.next;
            List<Integer> arr = new ArrayList<>();
            while (tmp != null) {
                arr.add(tmp.data);
                tmp = tmp.next;
            }
            int length = arr.size();
            for (int i = 0; i < length; i++) {
                if (i != arr.size() - 1) {
                    System.out.print(arr.get(i) + "--->");
                } else {
                    System.out.print(arr.get(i));
                }
            }

            System.out.println("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();
        int[] arr = new int[]{1, 2, 3, 4, 5};
        // 尾插法
        for (int i : arr) {
            linkedList.addNode(i);
        }
        // 头插法
//        for (int i : arr) {
//            linkedList.headInsert(i);
//        }
        // 删除指定结点
//        Node tmp = linkedList.head;
//        while (tmp.data != 2) {
//            tmp = tmp.next;
//        }
//        linkedList.removeSelectedNode(tmp);
        // 递归翻转链表
//        linkedList.head.next = linkedList.invertLinkedList(linkedList.head.next);
        // 迭代翻转链表
//        linkedList.iterationInvertLinkedList();
        // 翻转链表从结点 from 到结点 to
//        linkedList.iterationInvertLinkedList(2, 4);
        // 每 k 个一组翻转链表
//        linkedList.iteratorInvertLinkedListEveryK(2);
        // 逆序每 k 个一组翻转链表
        linkedList.reverseIterationInvertLinkedListEveryK(2);
        // 打印链表
        linkedList.printList();
    }
}

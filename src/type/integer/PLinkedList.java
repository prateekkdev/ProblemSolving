/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type.integer;

/**
 *
 * @author prateek.kesarwani
 */
public class PLinkedList {

    private Node head;

    public PLinkedList() {
        this.head = new Node();
    }

    /**
     * Inserted as first node.
     *
     * @param data
     */
    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.data = data;
        newNode.next = head.next;

        head.next = newNode;
    }

    public void insertLast(int data) {

        Node iterator = head;

        while (iterator.next != null) {
            iterator = iterator.next;
        }

        iterator.next = new Node(data);
    }

    /**
     * If pos not valid, insert at last
     *
     * @param data
     * @param pos
     */
    public void insertAt(int pos, int data) {
        Node iterator = head;
        int count = 1;

        while (iterator.next != null) {
            if (count == pos) {
                break;
            }

            count++;
            iterator = iterator.next;
        }

        // Two case for insert
        // 1. Ran out of position(pos given was out of list, so insert at last)
        // 2. Within range(We already breaked from above loop, so good to insert)
        Node newNode = new Node(data);
        newNode.next = iterator.next;
        iterator.next = newNode;

    }

    public void deleteFirst() {
        if (!isEmpty()) {
            head.next = head.next.next;
        }
    }

    public void deleteLast() {

        if (head.next != null) {

            Node iterator = head;

            while (iterator.next.next != null) {

                iterator = iterator.next;

                // This modifies head
                // DON'T EVER DO THAT
                // iterator.next = iterator.next.next;
            }

            iterator.next = null;
        }
    }

    public void delete(int data) {
        Node iterator = head;

        while (iterator.next != null) {
            if (iterator.next.data == data) {
                iterator.next = iterator.next.next;
                break;
            }
            iterator = iterator.next;
        }
    }

    public void deleteAt(int pos) {

        Node iterator = head;
        int count = 1;
        while (iterator.next != null) {
            if (count == pos) {
                iterator.next = iterator.next.next;
                break;
            }
            count++;
            iterator = iterator.next;
        }
    }

    public void traverse() {

        Node iterator = head.next;

        while (iterator != null) {
            System.out.print(iterator.data + ", ");
            iterator = iterator.next;
        }
        System.out.println();
    }

    public int fetchFirst() {

        if (isEmpty()) {
            return -1;
        } else {
            return head.next.data;
        }
    }

    public int fetchLast() {
        Node iterator = head.next;

        if (iterator != null) {
            while (iterator.next != null) {
                iterator = iterator.next;
            }

            return iterator.data;
        } else {
            return -1;
        }
    }

    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        } else {
            return false;
        }
    }

    public void reverseListPrint() {
        reverseListPrint(head);
    }

    public void reverseListPrint(Node node) {
        if (node == null) {
            return;
        }
        reverseListPrint(node.next);

        if (!node.equals(head)) {
            System.out.print(node.data + ", ");
        }
    }

    /**
     * This has time complexity O(n), but Space complexity O(1)
     */
    public void reverseList() {

        Node currNode = head.next;
        Node previousNode = null;

        while (currNode != null) {
            Node temp = currNode.next;
            currNode.next = previousNode;
            previousNode = currNode;
            currNode = temp;
        }
        head.next = previousNode;
    }

    public void reverseListRecursive() {
        if (head.next != null) {
            Node node = reverseListRescursive(head.next);
            node.next = null;
        }
    }

    /**
     * This has time complexity of O(n) and space complexity of O(n) as
     * well(Because recursion would be used n no. of times, which uses stack
     * internally).
     */
    public Node reverseListRescursive(Node node) {

        if (node == null) {
            return null;
        }

        Node reverseNode = reverseListRescursive(node.next);
        if (reverseNode == null) {
            head.next = node;
        } else {
            reverseNode.next = node;
        }

        return node;
    }

    /**
     *
     * http://www.geeksforgeeks.org/write-a-c-function-to-detect-loop-in-a-linked-list/
     *
     * Find Cycle in Linked List
     *
     * Below is Floyd’s Cycle-Finding Algorithm: Time Complexity O(n), Space
     * Complexity O(1)
     *
     * Other Methods:
     *
     * Hashing: Time Complexity O(n) Space Complexity O(n)
     *
     * Storing visited Nodes by modifying linked list data structure itself Time
     * Complexity O(n) Space Complexity O(n)
     *
     */
    public boolean isCycle() {

        Node slow = head.next;
        if (slow == null) {
            return false;
        }

        Node fast = head.next.next;
        if (fast == null) {
            return false;
        }

        while (!slow.equals(fast)) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == null || fast == null || fast.next == null) {
                return false;
            }

        }

        return true;
    }

    private class Node {

        private int data;
        private Node next;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node node) {
            this.next = node;
        }
    }
}

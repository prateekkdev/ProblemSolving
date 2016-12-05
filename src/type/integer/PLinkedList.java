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

    /**
     * Inserted as first node.
     *
     * Good practice to return head, which could be utilized by caller.
     * (Although not standard for libraries)
     *
     * @param data
     */
    public Node insert(int data) {
        if (head == null) {
            head = new Node(data);
            return head;
        }

        Node newNode = new Node(data);
        newNode.next = head.next;
        head = newNode;

        return head;
    }

    public Node insertLast(int data) {

        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return head;
        }

        Node iterator = head;

        while (iterator.next != null) {
            iterator = iterator.next;
        }

        iterator.next = newNode;

        return head;
    }

    /**
     * During insertion and deletion, always check if head is also getting
     * affected. Then we might need to modify head value, as below for position
     * 1 insert(Not just next).
     */
    public Node insertAt(int position, int data) {
        Node node = new Node();
        node.data = data;

        if (head == null) {
            this.head = node;
            return head;
        }

        if (position == 0) {
            node.next = head;
            this.head = node;
            return head;
        }

        Node current = head;
        int index = 1;

        // position - 1, because next would be the position.
        while (current.next != null && index < position - 1) {
            current = current.next;
            index++;
        }

        node.next = current.next;
        current.next = node;

        return head;
    }

    public Node deleteAt(int position) {

        if (head == null) {
            return head;
        }

        if (position == 0) {
            head = null;
            return head;
        }

        Node iterator = head;

        for (int index = 0; iterator.next != null && index < position - 1; index++, iterator = iterator.next);

        // This check is needed, if anyone passes wront index(Index off limits)
        if (iterator.next != null) {
            iterator.next = iterator.next.next;
        }

        return head;
    }

    public void print() {

        Node iterator = head;

        while (iterator != null) {
            System.out.print(iterator.data + ", ");
            iterator = iterator.next;
        }
        System.out.println();
    }

    public void reversePrint(Node head) {

        if (head == null) {
            return;
        }
        reversePrint(head.next);
        System.out.println(head.data);
    }

    public Node deleteFirst() {
        if (head != null) {
            head = head.next;
        }
        return head;
    }

    public Node deleteLast() {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            head = null;
            return head;
        }

        Node iterator = head;

        while (iterator.next.next != null) {

            // This modifies head
            iterator = iterator.next;
            // DON'T EVER DO THAT - It modifies LinkedList itself,
            // Rather than just iterating over.
            // iterator.next = iterator.next.next;
        }

        iterator.next = null;

        return head;
    }

    public Node delete(int data) {

        Node iterator = head;

        while (iterator.next.next != null) {

            if (iterator.next.data == data) {
                break;
            }

            iterator = iterator.next;
        }

        // Consider last node case as well, when iterator.next = data and iterator.next.next = null
        // Else we could have wrote inside loop as well.
        if (iterator.next.data == data) {
            iterator.next = null;
        }

        return head;
    }

    public int fetchFirst() {

        if (head == null) {
            return -1;
        } else {
            return head.data;
        }
    }

    public int fetchLast() {

        if (head == null) {
            return -1;
        }

        Node iterator = head;

        while (iterator.next != null) {
            iterator = iterator.next;
        }

        return iterator.data;
    }

    public int fetchAt(int position) {

        if (head == null) {
            return -1;
        }

        if (position == 0) {
            return head.data;
        }

        Node iterator = head.next;

        int index = 1;

        // Don't need to check for iterator.next
        // As we are directly returning from inside loop
        while (iterator != null) {

            if (index == position) {
                return iterator.data;
            }

            iterator = iterator.next;
            index++;
        }

        return -1;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    // ===========
    /**
     * This has time complexity O(n), but Space complexity O(1)
     */
    public void reverseList() {

        Node currNode = head;
        Node previousNode = null;

        while (currNode != null) {
            Node temp = currNode.next;
            currNode.next = previousNode;
            previousNode = currNode;
            currNode = temp;
        }
        head = previousNode;
    }

    public void reverseListRecursive() {
        reverseListRescursive(head);
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
            head = node;
        } else {
            reverseNode.next = node;
        }

        return node;
    }

    // Kth last element in linkedList
    public Node kthLast() {
        return null;
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

        Node slow = head;
        if (slow == null) {
            return false;
        }

        Node fast = head.next;
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

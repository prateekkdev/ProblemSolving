/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type.generic;

/**
 *
 * @author prateek.kesarwani
 */
public class PLinkedList<T> {

    private Node head;

    public PLinkedList() {
        this.head = new Node();
    }

    /**
     * Inserted as first node.
     *
     * @param data
     */
    public void insert(T data) {
        Node newNode = new Node(data);
        newNode.data = data;
        newNode.next = head.next;

        head.next = newNode;
    }

    public void insertLast(T data) {

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
    public void insertAt(int pos, T data) {
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

    public void delete(T data) {
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

    public T fetchFirst() {

        if (isEmpty()) {
            return null;
        } else {
            return head.next.data;
        }
    }

    public T fetch(int k) {

        Node iterator = head.next;

        int index = 1;

        while (iterator != null && index < k) {
            iterator = iterator.next;
            index++;
        }

        if (iterator != null && index == k) {
            return iterator.data;
        } else {
            return null;
        }
    }

    public T fetchLast() {
        Node iterator = head.next;

        if (iterator != null) {
            while (iterator.next != null) {
                iterator = iterator.next;
            }

            return iterator.data;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        } else {
            return false;
        }
    }

    private class Node {

        private T data;
        private Node next;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
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

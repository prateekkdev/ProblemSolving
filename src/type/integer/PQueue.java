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
public class PQueue {

    final private PLinkedList mLinkedList;

    public PQueue() {
        mLinkedList = new PLinkedList();
    }

    public void enqueue(int data) {
        mLinkedList.insertLast(data);
    }

    public int dequeue() {
        int data = mLinkedList.fetchFirst();
        mLinkedList.deleteFirst();
        return data;
    }

    public int peek() {
        return mLinkedList.fetchFirst();
    }

    public void display() {
        mLinkedList.print();
    }

    public boolean isEmpty() {
        return mLinkedList.isEmpty();
    }

}

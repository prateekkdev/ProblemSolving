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
public class PStack {

    final private PLinkedList mLinkedList;

    public PStack() {
        mLinkedList = new PLinkedList();
    }

    public void push(int data) {
        mLinkedList.insert(data);
    }

    public int pop() {
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

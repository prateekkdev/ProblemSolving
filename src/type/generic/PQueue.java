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
public class PQueue<T> {

    final private PLinkedList mLinkedList;

    public PQueue() {
        mLinkedList = new PLinkedList();
    }

    public void enqueue(T data) {
        mLinkedList.insertLast(data);
    }

    public T dequeue() {
        T data = (T) mLinkedList.fetchFirst();
        mLinkedList.deleteFirst();
        return data;
    }

    public T peek() {
        return (T) mLinkedList.fetchFirst();
    }

    public void display() {
        mLinkedList.traverse();
    }

    public boolean isEmpty() {
        return mLinkedList.isEmpty();
    }

}

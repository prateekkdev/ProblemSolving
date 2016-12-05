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
public class PStack<T> {

    final private PLinkedList mLinkedList;

    public PStack() {
        mLinkedList = new PLinkedList();
    }

    public void push(T data) {
        mLinkedList.insert(data);
    }

    public T pop() {
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

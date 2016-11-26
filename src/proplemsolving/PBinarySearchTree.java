/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proplemsolving;

import type.generic.*;

/**
 *
 * @author prateek.kesarwani
 */
public class PBinarySearchTree {

    public Node root;

    public PBinarySearchTree() {

    }

    // Insert using recursion
    public Node insert(Node root, int data) {

        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data <= root.data) {
            root.leftChild = insert(root.leftChild, data);
        } else {
            root.rightChild = insert(root.rightChild, data);
        }

        return root;
    }

    public void insert(int data) {

        // This is recursive approach
        root = insert(root, data);

        // This is iterative one
        /*
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = root;
        Node child = data <= parent.data ? parent.leftChild : parent.rightChild;

        while (child != null) {
            parent = child;
            child = data <= parent.data ? parent.leftChild : parent.rightChild;
        }

        if (data <= parent.data) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
         */
    }

    public Node delete(Node root, int data) {

        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.leftChild = delete(root.leftChild, data);
        } else if (data > root.data) {
            root.rightChild = delete(root.rightChild, data);
        } else // Found the key, so delete it
         if (root.leftChild == null) {
                return root.rightChild;
            } else if (root.rightChild == null) {
                return root.leftChild;
            } else {
                Node successor = minNode(root.rightChild);
                root.data = successor.data;

                root.rightChild = delete(root.rightChild, root.data);
            }

        return root;
    }

    public Node minNode(Node root) {
        if (root == null) {
            return null;
        }

        Node child = root.leftChild;
        while (child != null) {
            root = child;
            child = root.leftChild;
        }
        return root;
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    public Node search(Node root, int data) {

        // Value not found
        if (root == null) {
            return null;
        }

        // Value should be in left sub tree
        if (root.data > data) {
            return search(root.leftChild, data);
        }

        // Value should be in right sub tree
        if (root.data < data) {
            return search(root.rightChild, data);
        }

        // Value is in root
        return root;
    }

    /**
     * Tree Traversals
     *
     * i) BFS or Level Order(Queue could be applied), ii) DFS(Stack could be
     * used, only essential in Graphs though) - Could be preorder, inorder,
     * postorder
     */
    public void traverseLevelorder() {
        PQueue queue = new PQueue();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node node = (Node) queue.dequeue();
            System.out.print(node.data + ", ");

            if (node.leftChild != null) {
                queue.enqueue(node.leftChild);
            }

            if (node.rightChild != null) {
                queue.enqueue(node.rightChild);
            }
        }
    }

    public void traversePreorder() {
        PBinarySearchTree.this.traversePreorder(root);
    }

    private void traversePreorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + ", ");
        PBinarySearchTree.this.traversePreorder(node.leftChild);
        PBinarySearchTree.this.traversePreorder(node.rightChild);
    }

    public void traverseInorder() {
        traverseInorder(root);
    }

    private void traverseInorder(Node node) {

        if (node == null) {
            return;
        }

        traverseInorder(node.leftChild);
        System.out.print(node.data + ", ");
        traverseInorder(node.rightChild);
    }

    public void traversePostorder() {
        traversePostorder(root);
    }

    private void traversePostorder(Node node) {
        if (node == null) {
            return;
        }

        traversePostorder(node.leftChild);
        traversePostorder(node.rightChild);
        System.out.print(node.data + ", ");

    }

    public class Node {

        private Node leftChild;
        private Node rightChild;
        private int data;

        public Node(int data) {
            this.data = data;
        }

        public void setLeftChild(Node node) {
            this.leftChild = node;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setRightChild(Node node) {
            this.rightChild = node;
        }

        public Node getRightChild() {
            return rightChild;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

import type.generic.*;

/**
 * Some general Terminologies related to Trees:-
 *
 * Edge - Connection between one node to another
 *
 * Depth of a node – The depth of a node is the number of edges from the node to
 * the tree's root node. A root node will have a depth of 0.
 *
 * Path – a sequence of nodes and edges connecting a node with a descendant.
 *
 * Level – The level of a node is defined by 1 + the number of connections
 * between the node and the root. ie Level = Depth + 1. Or we can say total no
 * of edges b/w root and node(including both).
 *
 * Height of node – The height is the number of edges on the longest downward
 * path between that node and a leaf. A leaf node will have a height of 0.
 *
 * Height of tree(Also max depth of atree) - This is height of root node, ie
 * longest downward path between that node and a leaf node. Height of balanced
 * tree is long(n).
 *
 * Diameter - The diameter of a tree (sometimes called the width) is the number
 * of nodes on the longest path between two leaves in the tree.
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

        // This is iterative one - Working
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
        if (root != null) {
            queue.enqueue(root);
        }

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

    /**
     * http://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
     *
     * Top view of a binary tree is the set of nodes visible when the tree is
     * viewed from the top. Given a binary tree, print the top view of it. The
     * output nodes can be printed in any order. Expected time complexity is
     * O(n)
     */
    public void topView() {
        
    }

    /**
     * Implement for BST over here
     * http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
     */
    private Node lCA(Node root, int data1, int data2) {

        // TODO Need to check if data1 and data2 are available.
        if (data1 < root.data && data2 < root.data) {
            root = lCA(root.leftChild, data1, data2);
        } else if (data1 > root.data && data2 > root.data) {
            root = lCA(root.rightChild, data1, data2);
        }
        return root;
    }

    public int lowestCommonAncester(int data1, int data2) {

        // TODO: Could this be done more optimally in lCA?
        Node node1 = search(root, data1);
        Node node2 = search(root, data2);

        if (node1 != null && node2 != null) {
            return lCA(root, data1, data2).data;
        }

        return -1;
    }

    /**
     * Complexity O(n), as visits each node once in worst case.
     *
     * @param root
     * @param data
     * @return
     */
    public PLinkedList<Node> findPath(Node root, int data) {

        if (root == null) {
            return null;
        }

        PLinkedList<Node> list;

        if (root.data == data) {
            list = new PLinkedList<>();
            list.insert(root);
            return list;
        }

        list = findPath(root.leftChild, data);
        if (list != null) {
            list.insert(root);
            return list;
        }

        list = findPath(root.rightChild, data);
        if (list != null) {
            list.insert(root);
            return list;
        }

        return null;
    }

    /**
     *
     * As binary tree won't have sorted values, so need to find path first and
     * then try to find lowest ancestor.
     *
     * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
     *
     * Complexity: Worst is 3xO(n) - Two findPaths(2xO(n)) and iterating over
     * two lists at the same time(O(n))
     *
     * Plus extra space of 2xO(n)
     *
     * @param data1
     * @param data2
     */
    public int lowestCommonAncestorBinaryTree(int data1, int data2) {
        PLinkedList<Node> path1 = findPath(root, data1);
        if (path1 == null) {
            return -1;
        }

        PLinkedList<Node> path2 = findPath(root, data2);
        if (path2 == null) {
            return -1;
        }

        // If we reached here, so path1 and path2 are initialized with minimum one value(Starting with root node)
        int commonAncestor = root.data;
        while (!path1.isEmpty() && !path2.isEmpty()) {
            if (path1.fetchFirst().data == path2.fetchFirst().data) {
                commonAncestor = path1.fetchFirst().data;
                path1.deleteFirst();
                path2.deleteFirst();
                continue;
            }
            break;
        }

        return commonAncestor;
    }

    public int diameter() {
        if (root == null) {
            return -1;
        }

        return diameter(root);
    }

    /**
     * Complexity is O(n^2), because each node is visited twice, once for height
     * and then for diameter.
     *
     * @param root
     * @return
     */
    public int diameter(Node root) {
        if (root == null) {
            return 0;
        }

        int rootDiameter = 0;
        if (root.leftChild != null) {
            rootDiameter += height(root.leftChild) + 1;
        }

        if (root.rightChild != null) {
            rootDiameter += height(root.rightChild) + 1;
        }

        int maxChildDiameter = Math.max(diameter(root.leftChild), diameter(root.rightChild));

        return Math.max(rootDiameter, maxChildDiameter);
    }

    /**
     * Complexity should be O(n), Why can't height and diameter be computed in
     * same recursive call.
     *
     * @param root
     * @return
     */
    public int diameterOptimized(Node root) {
        return -1;
    }

    public int level(int data) {
        Node node1 = search(root, data);

        if (node1 != null) {
            return 1 + depth(root, data);
        }

        return -1;

    }

    public int depth(int data) {

        Node node1 = search(root, data);

        if (node1 != null) {
            return depth(root, data);
        }

        return -1;
    }

    private int depth(Node root, int data) {

        if (data < root.data) {
            return 1 + depth(root.leftChild, data);
        } else if (data > root.data) {
            return 1 + depth(root.rightChild, data);
        }

        return 0;
    }

    public int treeDepth() {
        if (root == null) {
            return -1;
        } else {
            return height(root);
        }
    }

    /**
     * Complexity is O(n), because each node is visited once.
     *
     * @return
     */
    public int treeHeight() {
        if (root == null) {
            return -1;
        } else {
            return height(root);
        }
    }

    private int height(Node root) {

        if (root == null
                || (root.leftChild == null && root.rightChild == null)) {
            return 0;
        }
        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
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

        @Override
        public String toString() {
            return "" + data;
        }

    }
}

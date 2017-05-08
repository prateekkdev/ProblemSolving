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
 * between the node and the root. ie Level = Depth + 1.
 *
 * Height of node – The height is the number of edges on the longest downward
 * path between that of node and a leaf. A leaf node will have a height of 0.
 *
 * Height of tree(Also max depth of a tree) - This is height of root node, ie
 * longest downward path between that node and a leaf node. Height of balanced
 * tree is log(n).
 *
 * Diameter - The diameter of a tree (sometimes called the width) is the number
 * of nodes on the longest path between two leaves in the tree.
 *
 * Notes: Usually Duplicates shouldn't be allowed in BST, for that some other
 * data structure should be employed. as duplicates would give issues while
 * rotation and everything and would make operations a little while adding a
 * little complexity since you may need to keep searching once you've found your
 * value for other nodes of the same value. In the pragmatic sense, that means
 * if the value is <>, you traverse the data structure in one of two
 * 'directions'. So, in that sense, duplicate values don't make any sense at
 * all. For more info:
 * http://stackoverflow.com/questions/300935/are-duplicate-keys-allowed-in-the-definition-of-binary-search-trees
 * But currently I think operations are based on allowing duplicates below.
 *
 *
 * @author prateek.kesarwani
 */
public class PBinarySearchTree {

    // public to access temporarily
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
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
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
        Node child = data <= parent.data ? parent.left : parent.right;

        while (child != null) {
            parent = child;
            child = data <= parent.data ? parent.left : parent.right;
        }

        if (data <= parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
         */
    }

    public Node delete(Node root, int data) {

        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else // Found the key, so delete it
        {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node successor = minNode(root.right);
                root.data = successor.data;

                root.right = delete(root.right, root.data);
            }
        }

        return root;
    }

    public Node minNode(Node root) {
        if (root == null) {
            return null;
        }

        Node child = root.left;
        while (child != null) {
            root = child;
            child = root.left;
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
            return search(root.left, data);
        }

        // Value should be in right sub tree
        if (root.data < data) {
            return search(root.right, data);
        }

        // Value is in root
        return root;
    }

    /**
     * This is done without using any extra storage
     *
     * Time Complexity is O(n^2), Space Complexity is O(1)
     *
     * @param root
     */
    void traverseLevelOrderNaive(Node root) {

        int height = height(root);

        for (int level = 1; level <= height; level++) {
            printGivenLevel(root, level);
        }

    }

    void printGivenLevel(Node root, int level) {
        if (root == null) {
            return;
        } else if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }
    }

    /**
     * Tree Traversals
     *
     * i) BFS or Level Order(Queue could be applied), ii) DFS(Stack could be
     * used, only essential in Graphs though) - Could be preorder, inorder,
     * postorder
     *
     * traverseLevelOrder - This is done using queue Time Complexity O(n) Space
     * Complexity O(n)
     */
    public void traverseLevelorder() {
        PQueue queue = new PQueue();
        if (root != null) {
            queue.enqueue(root);
        }

        while (!queue.isEmpty()) {
            Node node = (Node) queue.dequeue();
            System.out.print(node.data + ", ");

            if (node.left != null) {
                queue.enqueue(node.left);
            }

            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }

    public void traverseInorderIterative() {

    }

    /**
     * If root is null, return. Take a stack and push root. Iterate till stack
     * isEmpty. Pop top and push top's left child in stack, then top's right
     * child in stack and print top.
     */
    public void traversePreorderIterative() {

        if (root == null) {
            return;
        }

        PStack<Node> stack = new PStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }

            System.out.print(current.data + ", ");
        }
    }

    public void traversePreorder() {
        traversePreorder(root);
    }

    private void traversePreorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + ", ");
        PBinarySearchTree.this.traversePreorder(node.left);
        PBinarySearchTree.this.traversePreorder(node.right);
    }

    public void traverseInorder() {
        traverseInorder(root);
    }

    private void traverseInorder(Node node) {

        if (node == null) {
            return;
        }

        traverseInorder(node.left);
        System.out.print(node.data + ", ");
        traverseInorder(node.right);
    }

    public void traversePostorder() {
        traversePostorder(root);
    }

    private void traversePostorder(Node node) {
        if (node == null) {
            return;
        }

        traversePostorder(node.left);
        traversePostorder(node.right);
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
     * Basic topView, only leftmost child and rightmost child covered. Solved
     * here: https://www.hackerrank.com/challenges/tree-top-view
     *
     * @param root
     */
    public void top_view(Node root) {
        if (root == null) {
            return;
        }

        top_view(root.left, true);
        System.out.print(root.data + " ");
        top_view(root.right, false);
    }

    public void top_view(Node root, boolean isLeftChild) {

        if (root == null) {
            return;
        }

        if (isLeftChild) {
            top_view(root.left, isLeftChild);
            System.out.print(root.data + " ");
        } else {
            System.out.print(root.data + " ");
            top_view(root.right, isLeftChild);
        }

    }

    /**
     * Top View + Null elements
     *
     * @param root
     * @param isLeftChild
     * @param isChildNull
     */
    public void boundaryTraversal() {
        System.out.print(root.data + ", ");
        boundaryTraversal(root.left, true, false);
        boundaryTraversal(root.right, false, false);
    }

    private void boundaryTraversal(Node root, boolean isLeftChild, boolean isOnlyNullPrint) {

        if (root == null) {
            return;
        } else if (isOnlyNullPrint) {
            if (root.left == null && root.right == null) {
                System.out.print(root.data + ", ");
            } else {
                boundaryTraversal(root.left, isLeftChild, true);
                boundaryTraversal(root.right, isLeftChild, true);
            }
        } else if (isLeftChild) {
            System.out.print(root.data + ", ");
            boundaryTraversal(root.left, isLeftChild, false);
            boundaryTraversal(root.right, isLeftChild, true);
        } else {
            boundaryTraversal(root.left, isLeftChild, true);
            boundaryTraversal(root.right, isLeftChild, false);
            System.out.print(root.data + ", ");
        }
    }

    public void boundaryTraversalModular() {
        boundaryTraversalModular(root);
    }

    public void boundaryTraversalModular(Node root) {
        boundaryTraversalLeft(root.left);
        System.out.print(root.data + ", ");
        boundaryTraversalLeaves(root);
        boundaryTraversalRight(root.right);
    }

    // PreOrder, without right branch
    private void boundaryTraversalLeft(Node root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            return;
        }

        System.out.print(root.data + ", ");
        boundaryTraversalLeft(root.left);
    }

    // Inorder, for leaves only
    private void boundaryTraversalLeaves(Node root) {
        if (root == null) {
            return;
        }

        boundaryTraversalLeaves(root.left);
        if (root.left == null && root.right == null) {
            System.out.print(root.data + ", ");
            return;
        }
        boundaryTraversalLeaves(root.right);

    }

    // PostOrder, without left branch
    private void boundaryTraversalRight(Node root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            return;
        }

        boundaryTraversalRight(root.right);
        System.out.print(root.data + ", ");
    }

    /**
     *
     * http://www.geeksforgeeks.org/tree-isomorphism-problem/
     *
     * Two trees are called isomorphic if one of them can be obtained from other
     * by a series of flips, i.e. by swapping left and right children of a
     * number of nodes. Any number of nodes at any level can have their children
     * swapped. Two empty trees are isomorphic.
     *
     * @param n1
     * @param n2
     * @return
     */
    boolean isIsomorphic(Node n1, Node n2) {
        // Both roots are NULL, trees isomorphic by definition
        if (n1 == null && n2 == null) {
            return true;
        }

        // Exactly one of the n1 and n2 is NULL, trees not isomorphic
        if (n1 == null || n2 == null) {
            return false;
        }

        if (n1.data != n2.data) {
            return false;
        }

        // There are two possible cases for n1 and n2 to be isomorphic
        // Case 1: The subtrees rooted at these nodes have NOT been 
        // "Flipped". 
        // Both of these subtrees have to be isomorphic.
        // Case 2: The subtrees rooted at these nodes have been "Flipped"
        return (isIsomorphic(n1.left, n2.left)
                && isIsomorphic(n1.right, n2.right))
                || (isIsomorphic(n1.left, n2.right)
                && isIsomorphic(n1.right, n2.left));
    }

    /**
     * Implement for BST over here
     * http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
     */
    private Node lCA(Node root, int data1, int data2) {

        // TODO Need to check if data1 and data2 are available.
        if (data1 < root.data && data2 < root.data) {
            root = lCA(root.left, data1, data2);
        } else if (data1 > root.data && data2 > root.data) {
            root = lCA(root.right, data1, data2);
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
     * Complexity O(n), as visits each node once in worst case. This is
     * applicable for all binary tree(not just bst)
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

        list = findPath(root.left, data);
        if (list != null) {
            list.insert(root);
            return list;
        }

        list = findPath(root.right, data);
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
        if (root.left != null) {
            rootDiameter += height(root.left) + 1;
        }

        if (root.right != null) {
            rootDiameter += height(root.right) + 1;
        }

        int maxChildDiameter = Math.max(diameter(root.left), diameter(root.right));

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
            return 1 + depth(root.left, data);
        } else if (data > root.data) {
            return 1 + depth(root.right, data);
        }

        return 0;
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
                || (root.left == null && root.right == null)) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * This is only applicable for Binary Trees and not Binary Search trees. As
     * BST would never be a sum tree. So, once creating base class of BST, ie
     * PBinaryTree, would need to move this code over there.
     *
     * @param root
     * @return
     */
    public boolean isSumTree() {
        return isSumTree(root);
    }

    /**
     *
     * Get the sum of nodes in left subtree and right subtree. Check if the sum
     * calculated is equal to root’s data. Also, recursively check if the left
     * and right subtrees are SumTrees.
     *
     * Time Complexity: O(n^2)
     *
     * @param root
     * @return
     */
    private boolean isSumTreeNaive(Node root) {

        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        if (root.data == leftSum + rightSum && isSumTree(root.left) && isSumTree(root.right)) {
            return true;
        }

        return false;
    }

    /* Utility function to check if the given node is leaf or not */
    boolean isLeaf(Node node) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        return false;
    }

    /**
     *
     * This utilizes sum tree property, that root.data = 2*right.data +
     * 2*left.data
     *
     * Time Complexity: O(n)
     *
     * @param node
     * @return
     */
    boolean isSumTree(Node node) {
        int ls; // for sum of nodes in left subtree
        int rs; // for sum of nodes in right subtree

        /* If node is NULL or it's a leaf node then
         return true */
        if (node == null || isLeaf(node)) {
            return true;
        }

        if (isSumTree(node.left) && isSumTree(node.right)) {
            // Get the sum of nodes in left subtree
            if (node.left == null) {
                ls = 0;
            } else if (isLeaf(node.left)) {
                ls = node.left.data;
            } else {
                ls = 2 * (node.left.data);
            }

            // Get the sum of nodes in right subtree
            if (node.right == null) {
                rs = 0;
            } else if (isLeaf(node.right)) {
                rs = node.right.data;
            } else {
                rs = 2 * (node.right.data);
            }

            /* If root's data is equal to sum of nodes in left
               and right subtrees then return 1 else return 0*/
            if ((node.data == rs + ls)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private int sum(Node root) {

        if (root == null) {
            return 0;
        }

        return sum(root.left) + sum(root.right);
    }

    /**
     * Even these are only applicable for Binary Trees not BSTs.
     */
    public boolean areMirror(Node node1, Node node2) {

        // If both empty
        if (node1 == null && node2 == null) {
            return true;
        }

        // If one is empty
        if (node1 == null || node2 == null) {
            return false;
        }

        // If both are non-empty compare them recursively. 
        // Note that in recursive calls, 
        // we pass left of one tree and right of other tree
        return node1.data == node2.data
                && areMirror(node1.left, node2.right)
                && areMirror(node1.right, node2.left);

    }

    /**
     * Left and right child are mirror images.
     */
    public boolean isSymmetric() {
        return areMirror(root.left, root.right);
    }

    /**
     * Level order traversal in spiral form
     *
     * OR Printing a Binary Tree in Zig Zag Level-Order
     *
     * OR Print a binary tree in Zig Zag way.
     */
    public void traverseSpiral() {

    }

    /**
     * METHOD 1 Simple method with min and max being passed along for each node
     * recursively. Time Complexity: O(n) Space Complexity: O(1)
     */
    boolean isBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.data > min && root.data < max) {
            return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
        }

        return false;
    }

    /**
     * METHOD 2(Using In-Order Traversal) Do In-Order Traversal of the given
     * tree and store the result in a temp array. Then check if the temp array
     * is sorted in ascending order, if it is, then the tree is BST.
     *
     * Time Complexity: O(n)
     *
     * We can avoid the use of Auxiliary Array. While doing In-Order traversal,
     * we can keep track of previously visited node. If the value of the
     * currently visited node is less than the previous value, then tree is not
     * BST. This is big optimization for avoiding aux array.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     */
    Node prev;

    boolean isBST(Node root) {
        if (root == null) {
            return true;
        }

        if (!isBST(root.left)) {
            return false;
        }

        // False, if not orderered or duplicate values
        if (prev != null && root.data <= prev.data) {
            return false;
        }

        prev = root;

        // For left, previous node is previous left and for right previous node becomes root, as inorder
        return isBST(root.right);
    }

    /**
     * Modifiers are public, right now for understanding.
     */
    public static class Node {

        public Node left;
        public Node right;
        private int data;

        public Node(int data) {
            this.data = data;
        }

        public void setLeftChild(Node node) {
            this.left = node;
        }

        public Node getLeftChild() {
            return left;
        }

        public void setRightChild(Node node) {
            this.right = node;
        }

        public Node getRightChild() {
            return right;
        }

        @Override
        public String toString() {
            return "" + data;
        }

    }
}

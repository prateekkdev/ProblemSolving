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
        newNode.next = head;
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
        head = reverseList(head);
    }

    public Node reverseList(Node head) {

        if (head == null) {
            return null;
        }

        Node current = head;
        Node previous = null;

        while (current != null) {
            Node temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }

        this.head = previous;

        return this.head;
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

    public boolean hasCycle(Node head) {

        // head.next is checked for first value of fast, below
        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head.next.next;

        while (true) {
            if (slow == null) {
                return false;
            } else if (fast == null) {
                return false;
            } else if (fast.next == null) {
                return false;
            }

            // Compare objets, as then would be same a some point for cycle.
            if (slow.equals(fast)) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
    }

    public static void mergeLists(PLinkedList A, PLinkedList B) {
        mergeLists(A.head, B.head);
    }

    /**
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Could do with less code using another linkedList, but that would have
     * required O(n) space.
     *
     * We are merging B into A, would consider headA for mergedHead initially,
     * if need be would change it to point to headB.
     */
    public static Node mergeLists(Node headA, Node headB) {

        // So, when both are null, null would be returned.
        if (headA == null) {
            return headB;
        } else if (headB == null) {
            return headA;
        }

        // We will consider headA, if found to be larger than headB, then change.
        Node mergedHead = headA;

        Node currentA = headA;
        Node currentB = headB;
        Node mergedNode = null;

        while (currentA != null && currentB != null) {

            if (currentA.data <= currentB.data) {
                mergedNode = currentA;
                currentA = currentA.next;
            } else {
                Node tempB = currentB.next;
                currentB.next = currentA;

                // This means first node of B is larger, need to change head
                if (mergedNode == null) {
                    mergedHead = currentB;
                } else {
                    mergedNode.next = currentB;
                }

                currentB = tempB;
            }
        }

        if (currentA == null) {
            mergedNode.next = currentB;
        }

        if (currentB == null) {
            // No need to do anything, we are merging to A.
        }

        return mergedHead;
    }

    /**
     * First initialize index for k with -k, then when current reaches null, k
     * reaches kth node. Other similar solutions in
     * http://www.geeksforgeeks.org/nth-node-from-the-end-of-a-linked-list/
     *
     *
     * Alternative implementation could be first iterate the whole linked list
     * to find length, then reiterate for kth node.
     *
     * Alternative implementation could also be recursive, inwhich last
     * node(null) returns 0 then we add 1 on every return, till it becomes k
     * when we stop and print the data. If we want to return the value, then
     * could use some other datatype to return kth Node after kth node being
     * found.
     *
     * Complexity is O(n)
     *
     * @param k
     * @return
     */
    public int getKthLast(int k) {
        if (head == null) {
            return -1;
        }

        Node current = head;
        Node kthLast = null;

        int index = -1 * k;

        while (current != null) {
            if (index == 0) {
                kthLast = head;
            } else if (index > 0) {
                kthLast = kthLast.next;
            }
            current = current.next;
            index++;
        }

        if (index >= 0) {
            return kthLast.data;
        }

        // Means k is more than length of list
        return -1;
    }

    /**
     * Complexity O(n)
     */
    public Node removeDuplicates(Node head) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (previous != null && previous.data == current.data) {
                previous.next = current.next;
                // No need to set previous = current for this, as we are deleting current.
                // So only change previous.next and skip current(to delete that)
            } else {
                previous = current;
            }
            current = current.next;
        }
        return head;
    }

    /**
     * Here we are first finding length of A and B, and then starting with the
     * same place using length diff and comparing * * nodes. So, when nodes are
     * equal, gives our mergepoint.
     *
     * TODO Need to solve this recursively as well. Would be helpful in
     * understanding concepts.
     *
     * Complexity O(n)
     */
    int FindMergeNode(Node headA, Node headB) {

        int lengthA = 0;
        int lengthB = 0;

        Node iteratorA = headA;
        Node iteratorB = headB;
        while (iteratorA != null) {
            iteratorA = iteratorA.next;
            lengthA++;
        }

        while (iteratorB != null) {
            iteratorB = iteratorB.next;
            lengthB++;
        }

        iteratorA = headA;
        iteratorB = headB;

        int diff = lengthA - lengthB;

        // A has greater length than B
        if (diff > 0) {
            while (diff > 0) {
                iteratorA = iteratorA.next;
                diff--;
            }
        } else if (diff < 0) { // B has greater length than A
            while (diff < 0) {
                iteratorB = iteratorB.next;
                diff++;
            }
        }

        while (!iteratorA.equals(iteratorB)) {
            iteratorA = iteratorA.next;
            iteratorB = iteratorB.next;
        }

        // We are assuming this as is enforced by question itself.
        // iteratorA.data == iteratorB.data, then only code will reach here
        return iteratorA.data;

        /*
    if(headA.next == null && headB.next == null) {
        return Integer.MIN_VALUE;
    }
    
    
    
    if(headA.equals(headB)) {
        return headA.data;
    }
    
    return FindMergeNode(headA.next, headB.next);
         */
    }

    // TODO For Doubly linked list - Need to create one
    /*
    Node SortedInsert(Node head,int data) {
    
    Node node = new Node();
    node.data = data;
    node.prev = null;
    node.next = null;
    
    if(head == null) {
        return node;
    }
    
    Node current = head;
    
    while(current.next != null) {
        if(data > current.data && data <= current.next.data) {
            break;
        }
        current = current.next;
    }
    
    node.prev = current;
    node.next = current.next;
    current.next = node;
    
    return head;
    }
     */
    // Doubly linked list reverse
    /*
    Node Reverse(Node head) {
    
    
    if(head == null) {
        return head;
    }
    
    Node current = head;
    Node temp = null;
    while(current != null) {
        temp = current.prev;
        current.prev = current.next;
        current.next = temp;
        current = current.prev; // current.prev.next is still pointing to next node(Which we need to traverse)
    }
        
    // For single node cases(Even earlier head null check isn't required)
    if(prev != null) {
        head = temp.prev; // As already reversed, so temp.prev would have last element  
    }

    return head;
}
    
     */
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

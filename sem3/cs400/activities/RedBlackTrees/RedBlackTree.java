import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public Node(T data) { this.data = data; }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        public boolean isRightChild() {
            return parent!= null && parent.rightChild == this; 
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) { root = newNode; size++; return true; } // add first node to an empty tree
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            return returnValue;
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) return false;

            // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        // TODO: Implement this method.
        if(child.isLeftChild()) {
            // perform a right rotation
            // if the right child is null, assign its parent to the parent to avoid a NullPointerException
            if(child.rightChild != null) {
                child.rightChild.parent = parent; 
            }
            // make sure that the child's right child doesn't get lost during the rotation, and assign it to the parent to be rotated
            parent.leftChild = child.rightChild; 
            
            // this is to make sure that if the tree involved is a subtree, it will follow the general structure of the tree after rotation
            child.parent = parent.parent; 
            // if the parent is the root, just assign the root of the subtree to the child node
            if(parent.parent == null) {
                root = child; 
            }
            // else if the parent is a rightChild of the grandparent, make the subtree root the child
            else if (parent == parent.parent.rightChild) {
                parent.parent.rightChild = child; 
            }
            // same if the parent is a leftChild of the grandparent
            else {
                parent.parent.leftChild = child; 
            }
            // do the final rotation 
            child.rightChild = parent; 
            parent.parent = child; 
            return;

        }
        else if(!child.isLeftChild()) {
            // perform a left rotation 
            // just an exact mirror of the right rotation
           
            if(child.leftChild != null) {
                child.leftChild.parent  = parent; 
            }

            parent.rightChild = child.leftChild; 
            
            child.parent = parent.parent; 
            if (parent.parent == null) {
                root = child; 
            }
            else if(parent == parent.parent.leftChild) {
                parent.parent.leftChild = child; 
            }
            else {
                parent.parent.rightChild = child; 
            }
            child.leftChild = parent; 
            parent.parent = child; 
            return;

        }
        if(!(child.parent == parent)){
            throw new IllegalArgumentException("Child is adopted!");
        }
        return; 
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }


    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node){
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    
    // Implement at least 3 boolean test methods by using the method signatures below,
    // removing the comments around them and addind your testing code to them. You can
    // use your notes from lecture for ideas on concrete examples of rotation to test for.
    // Make sure to include rotations within and at the root of a tree in your test cases.
    // If you are adding additional tests, then name the method similar to the ones given below.
    // Eg: public static boolean test4() {}
    // Do not change the method name or return type of the existing tests.
    // You can run your tests by commenting in the calls to the test methods 

    public static boolean test1() {
        // TODO: Implement this test.
        boolean test = true; 
        RedBlackTree<Integer> rb1 = new RedBlackTree<>(); 
        rb1.insert(11); 
        rb1.insert(7); 
        rb1.insert(12); 
        rb1.insert(5);
        rb1.insert(9);
        rb1.insert(4);
        rb1.insert(13);
        rb1.insert(8);

        Node<Integer> a = rb1.root.leftChild;
        Node<Integer> b = rb1.root;
        rb1.rotate(a, b);
        if(rb1.toLevelOrderString().compareTo("[ 7, 5, 11, 4, 9, 12, 8, 13 ]") != 0) { 
            test = false; 
        }
        return test; 
    }

    
    public static boolean test2() {
        // TODO: Implement this test.
        boolean test = true; 
        RedBlackTree<Integer> rb1 = new RedBlackTree<>(); 
        rb1.insert(11); 
        rb1.insert(7); 
        rb1.insert(12); 
        rb1.insert(5);
        rb1.insert(9);
        rb1.insert(4);
        rb1.insert(13);
        rb1.insert(8);

        Node<Integer> a = rb1.root.rightChild;
        Node<Integer> b = rb1.root;
        rb1.rotate(a, b);
        if(rb1.toLevelOrderString().compareTo("[ 12, 11, 13, 7, 5, 9, 4, 8 ]") != 0) { 
            test = false; 
        }
        return test; 
    }

    public static boolean test3() {
        // TODO: Implement this test.
        boolean test = true; 
        RedBlackTree<Integer> rb1 = new RedBlackTree<>(); 
        rb1.insert(11); 
        rb1.insert(7); 
        rb1.insert(12); 
        rb1.insert(5);
        rb1.insert(9);
        rb1.insert(4);
        rb1.insert(13);
        rb1.insert(8);

        Node<Integer> a = rb1.root.rightChild.rightChild;
        Node<Integer> b = rb1.root.rightChild;
        rb1.rotate(a, b);
        if(rb1.toLevelOrderString().compareTo("[ 11, 7, 13, 5, 9, 12, 4, 8 ]") != 0) { 
            test = false; 
        }
        return test; 
    }

    
    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test 1 passed: " + test1());
        System.out.println("Test 2 passed: " + test2());
        System.out.println("Test 3 passed: " + test3());
        

    }

}

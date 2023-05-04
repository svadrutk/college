//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Linked Node
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////


/**
 * Creates a node of a linked list
 * @param <T>
 * @author svadrut
 */
public class LinkedNode<T> {
    private T data;
    private LinkedNode<T> next;

    /**
     * Default constructor for creating a linked node
     * @param data
     */
    public LinkedNode(T data) {
        this.data = data;
    }

    /**
     * Constructor for creating a linked node that also specifies what node points to
     * @param data
     * @param next
     */
    public LinkedNode(T data, LinkedNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Get what node is pointing to
     * @return next node
     */
    public LinkedNode<T> getNext() {
        return this.next;
    }

    /**
     * Get what node is storing
     * @return node data
     */
    public T getData() {
        return this.data;
    }

    /**
     * @Override toString()
     * @return string representation of node data
     */
    public String toString() {
        return this.data.toString();
    }

    /**
     * set what node is pointing to
     * @param next
     */
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }
}


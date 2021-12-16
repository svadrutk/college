//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tile List Iterator
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
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TileListIterator implements Iterator<Tile> {
    private Node element;
    private Node head;

    /**
     * Checks if stack has a next element
     * @return
     */
    @Override public boolean hasNext() {
        if(element == null) {
            return false;
        }
        return true;
    }

    /**
     * Iterates through stack by one
     * @return
     */
    @Override public Tile next() {
        if(element == null) {
            throw new NoSuchElementException("no such element left.");
        }
        else if(element.equals(head)) {
            element = element.getNext();
            return head.getTile();
        }
        else {
            Node result = element;
            element = element.getNext();
            return result.getTile();
        }
    }

    /**
     * Default constructor for the iterator
     * @param head
     */
    public TileListIterator(Node head) {
        // Creates a new iterator to iterate through a list of tiles starting from its head
        // head is a reference to the head of the linked list of tiles
        this.element = head;
        this.head = head;
    }
}

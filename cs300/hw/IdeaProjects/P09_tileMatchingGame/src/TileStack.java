//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tile Stack
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
import java.util.EmptyStackException;
import java.util.Iterator;

public class TileStack implements Iterable<Tile>, StackADT<Tile> {
    private Node top;
    private int size;

    /**
     * Default constructor for stack
     */
    public TileStack() {
        this.size = 0;
    }

    /**
     *
     * @return element at top of stack
     */
    public Tile peek() {
        if(this.size == 0) {
            throw new EmptyStackException();
        }
        else {
            return top.getTile();
        }
    }

    /**
     *
     * @return element at top of stack and remove same
     */
    public Tile pop() {
        if(this.size == 0) {
            throw new EmptyStackException();
        }
        else {
            Node tmp = top;
            top = top.getNext();
            size--;
            return tmp.getTile();
        }
    }
    public void push(Tile tile) {
        size++;
        if(top == null) {
            top = new Node(tile);
        } else {
            Node tmp = top;
            top = new Node(tile, tmp);
        }

    }
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public int size() {
        return size;
    }
    public java.util.Iterator<Tile> iterator() {
        return new TileListIterator(top);
    }
}

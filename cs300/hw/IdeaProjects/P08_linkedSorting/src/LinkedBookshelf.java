//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Linked Bookshelf
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
// Online Sources:  Piazza; helped for toString class
//
///////////////////////////////////////////////////////////////////////////////
/**
 * Class that creates a bookshelf that stores books; mutliple actions can be performed on the bookshelf.
 * @author svadrut
 */
public class LinkedBookshelf {
    private LinkedNode<Book> front;
    private LinkedNode<Book> back;
    private int size;
    private Attribute sortedBy;

    /**
     * Default constructor for bookshelf
     */
    public LinkedBookshelf() {
        this.front = null;
        this.back = null;
        this.size = 0;
        this.sortedBy = Attribute.ID;
    }

    /**
     * Accessor for size
     * @return size
     */
    public int size() {
        return this.size;
    }

    /**
     * Accessor to check if bookshelf is empty
     * @return if bookshelf is empty
     */
    public boolean isEmpty() {
        if(size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Override toString method
     * @return string representation of bookshelf
     */
    public String toString() {
        String output = sortedBy.toString() + "\n";
        output = output + front.toString() + "\n";
        for(int i = 1; i < size - 1; i++) {
            output = output + getNode(i) + "\n";
        }
        output = output + back.toString();
        output = output.strip();
        return output;
    }

    /**
     * Returns linkedList node at specified index
     * @param index
     * @return node of linkedList
     */
    public LinkedNode<Book> getNode(int index) {
        LinkedNode<Book> book = this.front;
        if(index == 0) {
            return book;
        }
        else if(index == size - 1) {
            book = this.back;
            return book;
        }
        else {
            for(int i = 0; i < index; i++ ) {
                book = book.getNext();
            }
        }
        return book;
    }

    /**
     * Returns linkedList book at specified index
     * @param index
     * @return
     */
    public Book get(int index) {
        LinkedNode<Book> book = this.front;
        if(index == 0) {
            return book.getData();
        }
        else if(index == size - 1) {
            book = this.back;
            return book.getData();
        }
        else {
            for(int i = 0; i < index; i++ ) {
                book = book.getNext();
            }
        }
        return book.getData();
    }

    /**
     * Get first book in bookshelf
     * @return first book
     */
    public Book getFirst() {
        return front.getData();
    }

    /**
     * Get last book in bookshelf
     * @return last book
     */
    public Book getLast() {
        return back.getData();
    }

    /**
     * Clear bookshelf
     */
    public void clear() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    /**
     * Add book to bookshelf
     * @param toAdd
     */
    public void appendBook(Book toAdd) {
        LinkedNode<Book> newNode = new LinkedNode<>(toAdd);
        if(this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            this.back.setNext(newNode);
            this.back = newNode;
        }
        size++;

    }

    /**
     * Insert book in bookshelf based on specified sorting method
     * @param toAdd
     */
    public void insertBook(Book toAdd) {
        LinkedNode<Book> newNode = new LinkedNode<>(toAdd);
        int index = 0;
        if(this.isEmpty()) {
            appendBook(toAdd);
        }
        else if(toAdd.compareTo(this.front.getData(), sortedBy) < 0) {
            newNode.setNext(this.front);
            this.front = newNode;
            size++;
        }
        else if(toAdd.compareTo(this.back.getData(), sortedBy) > 0) {
            appendBook(toAdd);
        }
        else {
            for(int i = 0; i < size; i++) {
                if(toAdd.compareTo(get(i), sortedBy) >= 0) {
                    index = i;
                }
            }
            newNode.setNext(getNode(index+1));
            getNode(index).setNext(newNode);
            size++;

        }
    }

    /**
     * Sort bookshelf based on specified sorting method a
     * @param b
     * @param a
     */
    public static void sort(LinkedBookshelf b, Attribute a) {
        b.sortedBy = a;
        LinkedBookshelf tmp = new LinkedBookshelf();
        tmp.sortedBy = a;
        if (!b.isEmpty() || !(b.size() == 1)) {
            for (int s = 0; s < b.size; s++) {
                tmp.insertBook(b.get(s));
            }
            b.clear();
            for (int z = 0; z < tmp.size; z++) {
                b.appendBook(tmp.get(z));
            }
        }

    }
}

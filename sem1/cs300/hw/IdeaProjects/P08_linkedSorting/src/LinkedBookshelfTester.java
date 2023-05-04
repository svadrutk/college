//////////////// LinkedBookshelfTester.java //////////////////////////
//
// Title:    This program is a tester class for LinkedBookshelf.java and LinkedNode.java
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    natu2@wisc.edu
// Lecturer: Mouna Kacem 
//
//////////////////// DID NOT WORK WITH A PAIR PROGRAMMER ///////////////////
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
///////////////////////// OUTSIDE HELP //////////////////////////
//
// Persons:         Ananya Aggarwall, Svadrut Kukunooru
// Online Sources:  Piazza
///////////////////////////////////////////////////////////////////////////////

/**
 * The class is created to tests the methods of the LinkedBookshelf class.
 * @author svadrut
 *
 */
public class LinkedBookshelfTester
{

  /**
   * Tests the LinkedNode class.
   * @return true if LinkedNode passes all tests and false otherwise
   */
  public static boolean testLinkedNode()
  {
    //Test case 1: Creating a LinkedNode using the one parameter constructor
    LinkedNode<Integer> node1 = new LinkedNode<Integer>(10);
    boolean test1 = false;
    if (! node1.getData().equals(10) || ! node1.toString().equals("10"))
      return false;
    try
    {
      if(node1.getNext().equals(null))
        test1 = true;
    }
    catch(NullPointerException e)
    {
      // expected behavior
      test1 = true;
    }
    //Test case 2: Creating a LinkedNode using two parameter constructor
      //passing in a String for data and a new LinkedNode for next
    boolean test2 = false;
    LinkedNode<String> node3 = new LinkedNode<String>("Zebra");
    LinkedNode<String> node2 = new LinkedNode<String>("Tree", node3);
    try
    {
      if(node3.getNext().equals(null))
        test2 = true;
      if (node2.getNext().equals(node3) && node2.getData().equals("Tree"))
        test2 = true;
    }
    catch(NullPointerException e)
    {
      // expected behavior
      test2 = true;
    }
    
    //Test Case 3: updating the next node of node3 (testing setNext() method)
    boolean passedTest3 = false;
    node3.setNext(new LinkedNode<String>("Jelly"));
    try
    {
      if(node3.getNext().equals(null))
        passedTest3 = false;
      if (node3.getNext().getData().equals("Jelly"))
        passedTest3 = true;
    }
    catch(NullPointerException e)
    {
      passedTest3 = false;
    }
    //Test Case 4: updating the next node of node1 (testing setNext() method)
    boolean passedTest4 = false;
    node1.setNext(new LinkedNode<Integer>(25));
    try
    {
      if(node1.getNext().equals(null))
        passedTest4 = false;
      if (node1.getNext().getData().equals(25) && node1.getNext().toString().equals("25"))
        passedTest4 = true;
    }
    catch(NullPointerException e)
    {
      passedTest4 = false;
    }
    System.out.println("testLinkedNode passes all tests");
    return test1 && test2 && passedTest3 && passedTest4; 
  }
  /**
   * Tests the clear() method in LinkedBookShelf
   * @return true is clear() passes all tests and false otherwise
   */
  public static boolean testClear()
  {
    //creating a new shelf
    LinkedBookshelf shelf = new LinkedBookshelf();
    //adding books to the shelf
    Book.resetGenerator();
    Book book1 = new Book("Harry Potter and the Sorcerer's Stone", 404, "Rowling", "J.K.");
    Book book2 = new Book("How to Win Friends and Influence People", 231, "Carnegie", "Dale");
    Book book3 = new Book("The Hunger Games", 354, "Collins", "Suzanne");
    shelf.appendBook(book1);
    shelf.appendBook(book2);
    shelf.appendBook(book3);
    //calling clear
    shelf.clear();
    // checking that size is 0 and isEmpty() returns true
    if ( ! (shelf.size() == 0) && ! (shelf.isEmpty()) ) 
    {
      return false;
    }
    //checking that back and front are null
    try
    {
      if (shelf.getFirst() == null && shelf.getLast() == null)
        return true;
    }
    catch (NullPointerException e) //expected exception: Front & back is supposed to be null.
    {
      System.out.println("testClear() has passed all tests.");
      return true;
    }
    System.out.println("testClear() did not pass tests");
    return false;
  }
  /**
   * Tests the appendBook() method in LinkedBookshelf
   * @return true if appendBook() passes all tests and false otherwise
   */
  public static boolean testAddBooks()
  {
    //creating a new shelf
    LinkedBookshelf shelf = new LinkedBookshelf();
    //adding books to the shelf
    Book.resetGenerator();
    Book book1 = new Book("Harry Potter and the Sorcerer's Stone", 404, "Rowling", "J.K.");
    Book book2 = new Book("How to Win Friends and Influence People", 231, "Carnegie", "Dale");
    Book book3 = new Book("The Hunger Games", 354, "Collins", "Suzanne");
    shelf.appendBook(book1);
    shelf.appendBook(book2);
    shelf.appendBook(book3);
    //Checking if the size is correct
    if (shelf.size() != 3)
    {  System.out.println("shelf is wrong size."); return false; }
    //checking that isEmpty returns false
    if (shelf.isEmpty())
    { System.out.println("shelf is still empty."); return false; }
    //checking that the order of elements is correct
    try
    {
      if(! (shelf.getFirst().equals(book1)) || ! (shelf.getLast().equals(book3)) 
          || ! (shelf.get(1).equals(book2)) )
      {  System.out.println("shelf is in the wrong order.");
         return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown");
      return false;
    }
    System.out.println("appendBooks() passes all tests");
    return true;
  }
  /**
   * Tests sort() method from LinkedBookShelf
   * @return true if passes all tests and false otherwise
   */
  public static boolean testSortBooks()
  {
    //creating a new shelf
    LinkedBookshelf shelf = new LinkedBookshelf();
    //adding books to the shelf
    Book.resetGenerator();
    Book book0 = new Book("Moby Dick", 2033, "Melville","Herman");
    Book book1 = new Book("Harry Potter and the Sorcerer's Stone", 404, "Rowling", "J.K.");
    Book book2 = new Book("How to Win Friends and Influence People", 231, "Carnegie", "Dale");
    Book book3 = new Book("The Hunger Games", 354, "Collins", "Suzanne");
    shelf.appendBook(book0);
    shelf.appendBook(book1);
    shelf.appendBook(book2);
    shelf.appendBook(book3);
    
    // sort by title
    shelf.sort(shelf, Attribute.TITLE);
    // checking that size hasn't changed
    if(shelf.size() != 4)
    {
      System.out.println("shelf is wrong size.");
      return false;
    }
    // checking that order is correct
    if(! (shelf.getFirst().equals(book1)) || ! (shelf.get(1).equals(book2))
        || !(shelf.get(2).equals(book0)) || ! (shelf.getLast().equals(book3)) )
    {
      System.out.println(shelf);
      System.out.println("shelf is in wrong order.");
      return false;
    }
    
    //sort using pageCount
    shelf.sort(shelf, Attribute.PAGECOUNT);

    //checking size
    if(shelf.size() != 4) {
      System.out.println("shelf is wrong size.");
      return false;
    }
    //checking that order is correct
    if(! (shelf.getFirst().equals(book2)) || ! (shelf.get(1).equals(book3))
        || !(shelf.get(2).equals(book1)) || ! (shelf.getLast().equals(book0)) ) {
      System.out.println(shelf);
      System.out.println("shelf is in wrong order.");
      return false;
    }
    
    System.out.println("testSortBooks() passes all tests");
    return true;
  }

  /**
   * Call all testers
   * @return if all testers are true
   */
  public static boolean runAllTests()
  {
    return (testLinkedNode() && testAddBooks() && testClear() && testSortBooks());

  }
  /**
   * @param args
   */
  public static void main(String[] args)
  {

    // remove a node from a chain of linked nodes
    LinkedNode<Integer> integers = new LinkedNode<Integer>(1, new LinkedNode<Integer>(2,
        new LinkedNode<Integer>(10, new LinkedNode<Integer>(5, new LinkedNode<Integer>(7)))));

      // integers --> 1 --> 2 --> 10 --> 5 --> 7
      // What will the following lines of code remove?
      LinkedNode<Integer> current = integers;
    LinkedNode<Integer> previous = null;
    while (current.getData() != 10) {
      previous = current;
      current = current.getNext(); // advance current
    }
    previous.setNext(current.getNext());
    System.out.println(current);

  }

}

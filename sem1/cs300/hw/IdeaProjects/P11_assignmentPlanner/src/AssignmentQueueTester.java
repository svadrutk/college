//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Assignment Queue Tester
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
// Persons:
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
/**
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 */
public class AssignmentQueueTester {
  /**
   * Tests the functionality of the constructor for AssignmentQueue Should implement at least the
   * following tests:
   *
   * - Calling the AssignmentQueue with an invalid capacity should throw an IllegalArgumentException
   * - Calling the AssignmentQueue with a valid capacity should not throw any errors, and should
   * result in a new AssignmentQueue which is empty, and has size 0
   *
   * @return true if the constructor of AssignmentQueue functions properly
   * @see AssignmentQueue#AssignmentQueue(int)
   */
  public static boolean testConstructor() {
    // TODO complete the implementation of this tester method

    boolean error = true;

    // testing constructor with invalid capacity
    try {
      AssignmentQueue queue = new AssignmentQueue(-1);
    } catch(IllegalArgumentException e) {

    } catch(Exception e) {
      System.out.println("Unexpected exception resulted from creating a constructor with an invalid capacity. ");
      error = false;
    }
    // testing valid constructor
    AssignmentQueue queue2 = new AssignmentQueue(3);
    if(queue2.isEmpty() != true) {
      error = false;
      System.out.println("isEmpty() method for the default constructor does not work. ");
    }
    if(queue2.size() != 0) {
      error = false;
      System.out.println("size of empty assignment queue is not zero. ");
    }
    if(error) {
      System.out.println("testConstructor() passes all tests. ");
    }
    return error; // default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of the enqueue() and peek() methods Should implement at least the
   * following tests:
   *
   * - Calling peek on an empty queue should throw a NoSuchElementException 
   * - Calling enqueue on a queue which is empty should add the Assignment, and increment the size 
   *   of the queue
   * - Calling enqueue on a non-empty queue should add the Assignment at the proper position, 
   *   and increment the size of the queue. Try add at least 5 assignments 
   * - Calling peek on a non-empty queue should always return the Assignment with the earliest due date
   * - Calling enqueue on a full queue should throw an IllegalStateException 
   * - Calling enqueue with a null Assignment should throw a NullPointerException
   *
   * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function properly
   */
  public static boolean testEnqueue() {
    // TODO complete the implementation of this tester method
    boolean error = true;

    AssignmentQueue queue = new AssignmentQueue(6);

    if(queue.size() != 0) {
      error = false;
    }
    // calling peek on an empty queue should throw an exception
    try {
      queue.peek();
    } catch(NoSuchElementException e) {

    } catch(Exception e) {
      System.out.println("Unexpected exception was thrown. ");
    }
    // add an assignment to an empty queue
    Assignment a = new Assignment("Math", 5, 5, 2);
    queue.enqueue(a);
    if(queue.size() != 1) {
      error = false;
      System.out.println("Queue size does not increment when you add it to an empty queue.");
    }
    // Calling enqueue on a non-empty queue should add the Assignment at the proper position,
    // and increment the size of the queue. Try adding at least 5 assignments
    Assignment b = new Assignment("English", 6, 1, 4);
    Assignment c = new Assignment("Social Studies", 1, 10, 2);
    Assignment d = new Assignment("Computer Science", 4, 23, 9);
    Assignment e = new Assignment("Statistics", 5, 14, 11);
    Assignment f = new Assignment("Artificial Intelligence", 10, 2, 3);
    //System.out.println("Before: " + queue);
    queue.enqueue(b);
    queue.enqueue(c);
    queue.enqueue(d);
    queue.enqueue(e);
    queue.enqueue(f);

    if(queue.size() != 6) {
      error = false;
      System.out.println("Queue size does not increment when you add it to a non-empty queue. ");
    }
    String correctPrint = c + "\n" + d + "\n" + a + "\n" + e + "\n" + b + "\n" + f + "\n";
    if(queue.toString().compareTo(correctPrint) != 0) {
      error = false;
      System.out.println("toString() method is incorrect. ");
    }
    // Calling peek on a non-empty queue should always return the Assignment with the earliest due date
    if(queue.peek().compareTo(c) != 0) {
      error = false;
      System.out.println("peek() is incorrect.");
    }
    // calling enqueue on a full queue should return an IllegalArgumentException
    try {
      queue.enqueue(c);
    } catch(IllegalArgumentException z) {

    } catch(Exception z) {
      error = false;
      System.out.println("An unexpected exception was thrown.");
    }
    // Calling enqueue with a null Assignment should throw a NullPointerException
    queue.clear();
    try {
      queue.enqueue(null);
    } catch (NullPointerException z){

    } catch(Exception z) {
      error = false;
      System.out.println("An unexpected exception was thrown.");
    }
    if(error) {
      System.out.println("testEnqueue() has passed all tests. ");
    }
    return error; // default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of dequeue() and peek() methods. The peek() method must return without
   * removing the assignment with the highest priority in the queue. The dequeue() method must
   * remove, and return the assignment with the highest priority in the queue. The size must be
   * decremented by one, each time the dequeue() method is successfully called. Try to check the
   * edge cases (calling peek and dequeue on an empty queue, and calling dequeue on a queue of size
   * one). For normal cases, try to consider dequeuing from a queue whose size is at least 6. Try to
   * consider cases where percolate-down recurses on left and right.
   * 
   * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function properly
   */
  public static boolean testDequeuePeek() {
    // TODO complete the implementation of this tester method
    boolean error = true;
    AssignmentQueue queue = new AssignmentQueue(8);
    if(queue.size() != 0) {
      error = false;
    }
    // test peek() and make sure it doesn't remove something from the queue
    Assignment a = new Assignment("Math", 5, 5, 2);
    Assignment b = new Assignment("English", 6, 1, 4);
    Assignment c = new Assignment("Social Studies", 1, 10, 2);
    Assignment d = new Assignment("Computer Science", 4, 23, 9);
    Assignment e = new Assignment("Statistics", 5, 14, 11);
    Assignment f = new Assignment("Artificial Intelligence", 10, 2, 3);

    queue.enqueue(a);
    queue.enqueue(b);
    queue.enqueue(c);
    queue.enqueue(d);
    queue.enqueue(e);
    queue.enqueue(f);

    queue.peek();
    if(queue.size() != 6) {
      error = false;
      System.out.println("peek() changes the size of the array. ");
    }
    // check dequeue from a normal populated queue
    Assignment dequeued = queue.dequeue();
    if(dequeued.compareTo(c) != 0) {
      error = false;
      System.out.println("dequeue() dequeues the wrong element. ");
    }
    if(queue.size() != 5) {
      error = false;
      System.out.println("dequeue() does not correctly decrement the size.");
    }
    dequeued = queue.dequeue();
    if(dequeued.compareTo(d) != 0) {
      error = false;
      System.out.println("dequeue() removes the wrong element.");
    }
    if(queue.size() != 4) {
      error = false;
      System.out.println("dequeue() does not correctly decrement the size. ");
    }
    dequeued = queue.dequeue();
    if(dequeued.compareTo(a) != 0) {
      error = false;
      System.out.println("dequeue() removes the wrong element.");
    }
    if(queue.size() != 3) {
      error = false;
      System.out.println("dequeue() does not correctly decrement the size. ");
    }
    // check dequeue from a queue with one element.
    queue.clear();
    queue.enqueue(a);
    queue.dequeue();
    if(queue.isEmpty() != true) {
      error = false;
      System.out.println("The queue is not empty after dequeuing the only element from a queue. ");
    }
    if(error) {
      System.out.println("testDequeuePeek() has passed all tests. ");
    }
    // check peek from a queue with one element
    queue.enqueue(a);
    if(queue.peek().compareTo(a) != 0) {
      error = false;
      System.out.println("peek() is incorrect for a queue with one element. ");
    }
    // check dequeue from an empty queue
    queue.clear();
    // dequeue
    try {
      queue.dequeue();
    } catch(NoSuchElementException g) {

    } catch(Exception g) {
      error = false;
      System.out.println("An unexpected exception occurred for dequeue().");
    }
    // peek
    try {
      queue.peek();
    } catch(NoSuchElementException g) {

    } catch(Exception g) {
      error = false;
      System.out.println("An unexpected exception occurred for peek()");
    }
    return error; // default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of the clear() method Should implement at least the following scenarios: 
   * - clear can be called on an empty queue with no errors 
   *  - clear can be called on a non-empty queue with no errors - After calling clear, the queue should contain no Assignments
   *
   * @return true if AssignmentQueue.clear() functions properly
   */
  public static boolean testClear() {
    // TODO complete the implementation of this tester method
    boolean error = true;
    AssignmentQueue queue = new AssignmentQueue(6);
    // test clear on an empty queue
    try {
      queue.clear();
    } catch(Exception e) {
      error = false;
      System.out.println("An unexpected exception occurred. ");
    }
    if(queue.isEmpty() != true) {
      error = false;
      System.out.println("isEmpty() returned false when it should return true. ");
    }
    // create a queue and clear it
    Assignment a = new Assignment("Math", 5, 5, 2);
    Assignment b = new Assignment("English", 6, 1, 4);
    Assignment c = new Assignment("Social Studies", 1, 10, 2);
    Assignment d = new Assignment("Computer Science", 4, 23, 9);
    Assignment e = new Assignment("Statistics", 5, 14, 11);
    Assignment f = new Assignment("Artificial Intelligence", 10, 2, 3);

    queue.enqueue(a);
    queue.enqueue(b);
    queue.enqueue(c);
    queue.enqueue(d);
    queue.enqueue(e);
    queue.enqueue(f);

    queue.clear();
    // test if dequeue, peek, etc. works as expected
    // isEmpty
    if(queue.isEmpty() != true) {
      error = false;
      System.out.println("isEmpty() returned false when it should return true. ");
    }
    // dequeue
    try {
      queue.dequeue();
    } catch(NoSuchElementException g) {

    } catch(Exception g) {
      error = false;
      System.out.println("An unexpected exception occurred for dequeue().");
    }
    // peek
    try {
      queue.peek();
    } catch(NoSuchElementException g) {

    } catch(Exception g) {
      error = false;
      System.out.println("An unexpected exception occurred for peek()");
    }

    if(error) {
      System.out.println("testClear() has passed all tests. ");
    }
    return error;
  }

  /**
   * Tests all the methods of the AssignmentQueue class
   * 
   */
  public static boolean runAllTests() {
    // TODO complete the implementation of this tester method
    try {
      return testConstructor() && testEnqueue() && testClear() && testDequeuePeek();
    } catch(Exception e) {
      return false;
    }
  }
  
  /**
   * Main method
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    runAllTests();
  }
}

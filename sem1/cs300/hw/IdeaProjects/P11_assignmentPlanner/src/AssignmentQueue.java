//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Assignment Queue
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
// Persons:         Saili Natu for her percolateDown
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Assignments. Guarantees the
 * min-heap invariant, so that the Assignment at the root should have the earliest due date, and
 * children always have a due date after or at the same time as their parent. The root of a
 * non-empty queue is always at index 0 of this array-heap.
 */
public class AssignmentQueue implements PriorityQueueADT<Assignment>, Iterable<Assignment> {
  private Assignment[] queue; // array min-heap of assignments representing this priority queue
  private int size; // size of this priority queue


  /**
   * Creates a new empty AssignmentQueue with the given capacity
   * 
   * @param capacity Capacity of this AssignmentQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public AssignmentQueue(int capacity) {
    // TODO complete this implementation
    if(capacity <= 0) {
      throw new IllegalArgumentException("capacity is not a positive integer.");
    }
    this.size = 0;
    this.queue = new Assignment[capacity];

  }

  /**
   * Checks whether this AssignmentQueue is empty
   * 
   * @return {@code true} if this AssignmentQueue is empty
   */
  @Override
  public boolean isEmpty() {
    // TODO complete this implementation
    if(this.size == 0) {
      return true;
    }
    return false; // default return statement added to resolve compile errors
  }

  /**
   * Returns the size of this AssignmentQueue
   * 
   * @return the size of this AssignmentQueue
   */
  @Override
  public int size() {
    // TODO complete this implementation
    if(this.size >= 0) {
      return this.size;
    }
    return -1; // default return statement added to resolve compile errors
  }

  /**
   * Returns the capacity of this AssignmentQueue
   * 
   * @return the capacity of this AssignmentQueue
   */
  public int capacity() {
    // TODO complete this implementation
    if(this.queue != null) {
      return this.queue.length;
    }
    return 0; // default return statement added to resolve compile errors
  }
  
  
  /**
   * Removes all elements from this AssignmentQueue
   */
  @Override
  public void clear() {
    // TODO complete this implementation
    this.size = 0;
    for(int i = 0; i < capacity(); i++) {
      queue[i] = null;
    }
  }
  
  /**
   * Returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment with the
   * earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException if this AssignmentQueue is empty
   */
  @Override
  public Assignment peek() {
    // TODO complete this implementation
    if(this.size <= 0) {
      throw new NoSuchElementException("The AssignmentQueue is empty.");
    }
    return queue[0];
  }


  /**
   * Adds the given Assignment to this AssignmentQueue at the correct position based on the min-heap
   * ordering. This queue should maintain the min-heap invariant, so that the Assignment at each
   * index has an earlier or equivalent due-date than the Assignments in its child nodes.
   * Assignments should be compared using the Assignment.compareTo() method.
   * 
   * @param e Assignment to add to this AssignmentQueue
   * @throws NullPointerException  if the given Assignment is null
   * @throws IllegalStateException with a descriptive error message if this AssignmentQueue is full
   */
  @Override
  public void enqueue(Assignment e) {
    if(e == null) {
      throw new NullPointerException("Assignment is null.");
    }
    if(this.size() == this.capacity()) {
      throw new IllegalArgumentException("AssignmentQueue is full.");
    }
    // TODO complete this implementation
    // insert element at end of queue
    if(size == 0) {
      queue[size] = e;
      size = 1;
    }
    else {
      queue[size] = e;
      size++;
      percolateUp(size-1);
    }
  }

  /**
   * Removes and returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment
   * with the earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException with a descriptive error message if this AssignmentQueue is
   *                                empty
   */
  @Override
  public Assignment dequeue() {
    // TODO complete this implementation
    if(this.size == 0) {
      throw new NoSuchElementException("AssignmentQueue is empty.");
    }
    Assignment front = queue[0];
    queue[0] = queue[size - 1];
    queue[size - 1] = front;
    size--;

    percolateDown(0);
    return front;

  }

  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap invariant of a given
   * subtree by percolating its root down the tree. If the element at the given index does not
   * violate the min-heap invariant (it is due before its children), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int i)
  {
    // Time complexity: O(log N)
    if((2*i + 2) < size()) //if there is a right child
    //which means there must also be a left child
    {
      //Finding the assignment with the highest priority out of the element at i, 2i + 1 ad 2i + 2
      int indexSmallest = 2*i + 2;
      if(queue[2*i + 1].compareTo(queue[indexSmallest]) < 0)
        indexSmallest = 2*i + 1;
      if(queue[indexSmallest].compareTo(queue[i]) < 0) // ordering property violated
      {
        Assignment tmp = queue[i];
        queue[i] = queue[indexSmallest];
        queue[indexSmallest] = tmp;
        percolateDown(indexSmallest);
      }
      else //ordering property satisfied
        return; //heap should not be modified
    }
    else if((2*i + 1) < size()) //if there is only a left child
    {
      if(queue[2*i + 1].compareTo(queue[i]) < 0) //ordering property violate
      {
        Assignment tmp = queue[i];
        queue[i] = queue[2*i + 1];
        queue[2*i + 1] = tmp;
        percolateDown(2*i + 1);
      }
      else
        return;
    }
    else
      return;
  }


  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of the tree
   * by percolating a leaf up the tree. If the element at the given index does not violate the
   * min-heap invariant (it occurs after its parent), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int i) {
    // TODO provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(log n)

    // TODO complete this implementation. This method MUST be implemented recursively
    if(i >= queue.length) {
      throw new IndexOutOfBoundsException("Given index is out of bounds.");
    }

    // switch around parent and child index if child is lesser than parent using compareTo method
    if(queue[i].compareTo(queue[(i - 1) / 2]) < 0) {
      Assignment tmp = queue[(i - 1) / 2];
      queue[(i - 1) / 2] = queue[i];
      queue[i] = tmp;
      percolateUp((i - 1)/2);
    }
    // recursively implement method if parent of newly switched child is also greater

    
  }
  /**
   * Returns a deep copy of this AssignmentQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate 
   * assignments. Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this AssignmentQueue. The returned new assignment queue has the same
   *         length and size as this queue.
   */
  public AssignmentQueue deepCopy() {
    // TODO complete this implementation
    AssignmentQueue newQueue = new AssignmentQueue(this.queue.length);

    for(int i = 0; i < this.size(); i++) {
      newQueue.enqueue(queue[i]);
    }
    return newQueue; // default return statement added to resolve compiler errors
  }

  /**
   * Returns a String representing this AssignmentQueue, where each element (assignment) of the
   * queue is listed on a separate line, in order from earliest to latest.
   * 
   * @see Assignment#toString()
   * @see AssignmentIterator
   * @return a String representing this AssignmentQueue
   */
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Assignment a : this) {
      val.append(a).append("\n");
    }

    return val.toString();
//    return Arrays.toString(queue);
  }

  /**
   * Returns an Iterator for this AssignmentQueue which proceeds from the earliest to the latest
   * Assignment in the queue.
   * 
   * @see AssignmentIterator
   * @return an Iterator for this AssignmentQueue
   */
  @Override
  public Iterator<Assignment> iterator() {
    return new AssignmentIterator(this);
  }
}

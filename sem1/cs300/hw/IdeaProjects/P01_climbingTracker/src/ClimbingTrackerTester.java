//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Course: CS 300 Fall 2020
//
// Author: Zayne Zeichert
// Email: (zazeichert@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Svadrut Kukunooru
// Partner Email: kukunooru@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __x_ Write-up states that pair programming is allowed for this assignment.
// __x_ We have both read and understand the course Pair Programming Policy.
// __x_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: PO1 Pre-Assignment - helped us convert strings to ints in order to fulfill
//////////////// certain methods
////////////////
//////////////// https://docs.oracle.com/en/java/javase/11/docs/api/allclasses.html - helped us with
//////////////// String, Integer, and Character class to complete method requirements
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class contains all of the tests used for the ClimbingTracker class
 * 
 * @author Zayne Zeichert and Svadrut Kukunooru
 *
 */
public class ClimbingTrackerTester {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    runAllTests();
  }

  /**
   * Checks whether testSendClimb() works as expected
   * 
   * @return true if method works as intended, false otherwise
   */
  public static boolean testSendClimb() {

    String[] send = new String[5];
    int size = 0;

    // gets the size of send
    for (int i = 0; i < send.length; i++) {
      if (send[i] != null) {
        size++;
      }
    }

    int test = ClimbingTracker.sendClimb(send, size, "V0");

    if (test == size + 1 || test == size) { // if the output is the original size or size + 1 the
                                            // test passes and returns true
      return true;
    }

    return false;
  }

  /**
   * Checks whether testFailClimb() works as expected
   * 
   * @return true if method works as intended, false otherwise
   */
  public static boolean testFailClimb() {

    String[] fail = new String[5];
    int size = 0;

    // gets the size of fail
    for (int i = 0; i < fail.length; i++) {
      if (fail[i] != null) {
        size++;
      }
    }

    int test = ClimbingTracker.failClimb(fail, size, "V0");

    if (test == size + 1 || test == size) { // if the output is the original size or size + 1 the
                                            // test passes and returns true
      return true;
    }

    return false;
  }

  /**
   * Checks whether testGetStats() works as expected
   * 
   * @return true if method works as intended, false otherwise
   */
  public static boolean testGetStats() {

    String[] send = new String[10];
    String[] fail = new String[10];
    send[0] = "V1";
    send[1] = "V2";
    send[2] = "V3";
    fail[0] = "V3";
    fail[1] = "V2";

    // gets the values in send
    int numSend = 0;
    for (int i = 0; i < send.length; i++) {
      if (send[i] != null) {
        numSend++;
      }
    }

    // gets the values in fail
    int numFail = 0;
    for (int i = 0; i < fail.length; i++) {
      if (fail[i] != null) {
        numFail++;
      }
    }

    String test = ClimbingTracker.getStats(send, numSend, fail, numFail, 2);
    String shouldEqual = "send: 2.5\nfail: 2.5";

    if (test.equals(shouldEqual)) { // tests to see if the return statement equals the shouldEqual
                                    // string
      return true;
    }

    return false;
  }

  /**
   * Checks whether testGetHistogram() works as expected
   * 
   * @return true if method works as intended, false otherwise
   */
  public static boolean testGetHistogram() {

    String[] send = new String[] {"V1", "V1", "V2", "V0", null};
    String[] fail = new String[] {"V3", "V1", null, null, null};


    String test = ClimbingTracker.getHistogram(send, 4, fail, 2);
    String shouldEqual = "V0: + \nV1: - + + \nV2: + \nV3: - \n";

    if (test.equals(shouldEqual)) { // tests if the string shouldEqual is the same as the statement
                                    // returned from the method call ClimbingTracker.getHistogram
      return true;
    }
    return false;
  }

  /**
   * The method runAllTests() verifies that entire program works
   * 
   * @return true if all the tests pass, false if one fails
   */
  public static boolean runAllTests() {
    if (testSendClimb()) {
      if (testFailClimb()) {
        if (testGetStats()) {
          if (testGetHistogram()) {
            return true; // if all tests return true then runAllTests() works as intended and
                         // returns true
          }
        }
      }
    }

    return false;
  }


}

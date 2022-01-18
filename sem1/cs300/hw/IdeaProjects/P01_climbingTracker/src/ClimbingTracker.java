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
 * This class contains all of the methods tested by the ClimbingTrackerTester class
 * 
 * @author Zayne Zeichert and Svadrut Kukunooru
 *
 */
public class ClimbingTracker {

  /**
   * This method adds a successfully completed climb's grade. Checks to make sure it is a valid
   * input and/or if there is room in the oversize array
   * 
   * @param send    name the oversize array being used
   * @param numSend the existing size of send
   * @param grade   the name of successful grade level
   * @return the size of the updated oversize array if the size changes. If the array is full,
   *         return the existing size with no changes
   */
  public static int sendClimb(String[] send, int numSend, String grade) {

    if (grade.length() == 2) {
      if (grade.substring(0, 1).toUpperCase().equals("V")) {
        if (Character.isDigit(grade.charAt(1))) {
          if ((Integer.parseInt(grade.substring(1)) >= 0)
              && (Integer.parseInt(grade.substring(1)) <= 7)) {
            if (numSend < send.length) {
              send[numSend] = grade;
              return numSend + 1;
            }
          }
        }
      }
    }
    return numSend;
  }

  /**
   * This method follows the same format as sendClimb(), but instead of successful climbs, it
   * consists of failed climb's grade
   * 
   * @param fail    name of the oversize array being used
   * @param numFail the existing size of the oversize array
   * @param grade   name of the failed grade level
   * @return the size of the updated oversize array if the size changes. If the array is full,
   *         return the existing size with no changes
   */
  public static int failClimb(String[] fail, int numFail, String grade) {

    if (grade.length() == 2) {
      if (grade.substring(0, 1).toUpperCase().equals("V")) {
        if (Character.isDigit(grade.charAt(1))) {
          if ((Integer.parseInt(grade.substring(1)) >= 0)
              && (Integer.parseInt(grade.substring(1)) <= 7)) {
            if (numFail < fail.length) {
              fail[numFail] = grade;
              return numFail + 1;
            }
          }
        }
      }
    }
    return numFail;
  }

  /**
   * This method gets the average of the recent climbs for both send and fail
   * 
   * @param send          name of an oversize array containing successful climbs
   * @param numSend       size of the send array
   * @param fail          name of an oversize array containing failed climbs
   * @param numFail       size of the fail array
   * @param historyLength the most recent amount of grades added together, then divided by this
   *                      value
   * @return the average double value of the most recent grade levels added together corresponding
   *         to the value of historyLength
   */
  public static String getStats(String[] send, int numSend, String[] fail, int numFail,
      int historyLength) {

    double sendAvg = 0.0;
    double failAvg = 0.0;

    // this first if statement checks if historyLength is 0 or less
    if (historyLength <= 0) {
      return "send: -- \nfail: --";
    }

    // this checks if numSend is 0, then tests the rest of the possibilities numFail can be
    else if (numSend == 0) {
      if (numFail == 0) {
        return "send: -- \nfail: --";
      } else if (numFail < historyLength) {
        for (int i = 0; i < numFail; i++) {
          failAvg = 1.0 * Integer.parseInt(fail[i].substring(1)) + failAvg;
        }
        return "send: --\nfail: " + failAvg / numFail;
      } else {
        for (int i = numFail - 1; i >= numFail - historyLength; i--) {
          failAvg = 1.0 * Integer.parseInt(fail[i].substring(1)) + failAvg;
        }
        return "send: --\nfail: " + failAvg / historyLength;
      }
    }

    // this checks if numFail is 0, then tests the rest of the possibilities numSend can be
    else if (numFail == 0) {
      if (numSend == 0) {
        return "send: -- \nfail: --";
      } else if (numSend < historyLength) {
        for (int i = 0; i < numSend; i++) {
          sendAvg = 1.0 * Integer.parseInt(send[i].substring(1)) + sendAvg;
        }
        return "send: " + sendAvg / numSend + "\nfail: --";
      } else {
        for (int i = numSend - 1; i >= numSend - historyLength; i--) {
          sendAvg = 1.0 * Integer.parseInt(send[i].substring(1)) + sendAvg;
        }
        return "send: " + sendAvg / historyLength + "\nfail: --";
      }
    }

    // this checks if historyLength is greater than numSend, then tests the rest of the
    // possibilities numFail can be
    else if (historyLength > numSend) {
      for (int i = 0; i < numSend; i++) {
        sendAvg = 1.0 * Integer.parseInt(send[i].substring(1)) + sendAvg;
      }
      if (historyLength > numFail) {
        for (int i = 0; i < numFail; i++) {
          failAvg = 1.0 * Integer.parseInt(fail[i].substring(1)) + failAvg;
        }
        return "send: " + sendAvg / numSend + "\nfail: " + failAvg / numFail;
      } else {
        for (int i = numFail - 1; i >= numFail - historyLength; i--) {
          failAvg = 1.0 * Integer.parseInt(fail[i].substring(1)) + failAvg;
        }
        return "send: " + sendAvg / numSend + "\nfail: " + failAvg / historyLength;
      }
    }

    // this checks if historyLength is greater than numFail, then tests the rest of the
    // possibilities numSend can be
    else if (historyLength > numFail) {
      for (int i = 0; i < numFail; i++) {
        failAvg = 1.0 * Integer.parseInt(fail[i].substring(1)) + failAvg;
      }
      if (historyLength > numSend) {
        for (int i = 0; i < numSend; i++) {
          sendAvg = 1.0 * Integer.parseInt(send[i].substring(1)) + sendAvg;
        }
        return "send: " + sendAvg / numSend + "\nfail: " + failAvg / numFail;
      } else {
        for (int i = numSend - 1; i >= numSend - historyLength; i--) {
          sendAvg = 1.0 * Integer.parseInt(send[i].substring(1)) + sendAvg;
        }
        return "send: " + sendAvg / historyLength + "\nfail: " + failAvg / numFail;
      }
    }

    // all other tests failed, calculate average normal without extreme cases
    else {
      for (int i = numSend - 1; i >= numSend - historyLength; i--) {
        sendAvg = 1.0 * Integer.parseInt(send[i].substring(1)) + sendAvg;
      }
      for (int i = numFail - 1; i >= numFail - historyLength; i--) {
        failAvg = 1.0 * Integer.parseInt(fail[i].substring(1)) + failAvg;
      }
      return "send: " + sendAvg / historyLength + "\nfail: " + failAvg / historyLength;
    }

  }

  /**
   * This method creates a String consisting of "-" and "+" based on failing or passing a grade
   * 
   * @param send    name of the oversize array containing passed grades
   * @param numSend amount of passed grades in the array send
   * @param fail    name of the oversize array containing failed grades
   * @param numFail amount of failed grades in the array fail
   * @return
   */
  public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {

    if (numFail == 0 && numSend == 0) {
      return "Error: no data to display";
    }
    StringBuilder result = new StringBuilder();
    int highestGrade = 0;
    for (int a = 0; a < numSend; a++) {
      if ((Integer.parseInt(send[a].substring(1)) > highestGrade)) {
        highestGrade = Integer.parseInt(send[a].substring(1));
      }
    }
    for (int a = 0; a < numFail; a++) {
      if ((Integer.parseInt(fail[a].substring(1)) > highestGrade)) {
        highestGrade = Integer.parseInt(fail[a].substring(1));
      }
    }
    for (int i = 0; i <= highestGrade; i++) {
      result.append("V").append(i).append(": ");
      for (int j = 0; j < numFail; j++) {
        if (fail[j].equals("V" + i)) {
          result.append("- ");
        }
      }
      for (int k = 0; k < numSend; k++) {
        if (send[k].equals("V" + i)) {
          result.append("+ ");
        }
      }
      result.append("\n");
    }
    return result.toString();
  }



}

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Exceptional Climbing Tester
// Course: CS 300 Fall 2020
//
// Author: Svadrut Kukunooru
// Email: kukunooru@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:
// Partner Email:
// Partner Lecturer's Name:
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: PO1 Pre-Assignment - helped us convert strings to ints in order to fulfill
//
///////////////////////////////////////////////////////////////////////////////
import java.util.zip.DataFormatException;

/**
 * This class contains all the tests used for the ExceptionalClimbing class
 *
 * @author Svadrut Kukunooru
 *
 */
public class ExceptionalClimbingTester {

    public static void main(String[] args) throws DataFormatException {
        runAllTests();
    }

    /**
     * Checks whether testSendClimb() works as expected
     *
     * @return true if method works as intended, false otherwise
     */
    public static boolean testSendClimb() {
        System.out.println("---------------------");
        System.out.println("sendClimb test method");
        System.out.println("---------------------");
        String[] send = new String[5];
        int size = 0;
        String[] fullArray = new String[]{"V2", "V3"};
        int fullArraySize = 2;
        String[] nullArray = new String[]{"V2", "V4", null, "V6", "V7"};
        int nullArraySize = 4;

        // valid input
        try {
            ExceptionalClimbing.sendClimb(send, size, "V7");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // invalid grade
        try {
            ExceptionalClimbing.sendClimb(send, size, "V8");
            return false;
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("V8 is not a valid grade")) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // full size array
        try {
            ExceptionalClimbing.sendClimb(fullArray, fullArraySize, "V2");
            return false;
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("cannot add new value to full length 2 array")) {
                return false;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // numSend is less than 0
        try {
            ExceptionalClimbing.sendClimb(send, -1, "V4");
            return false;
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("invalid oversize array")) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // null element array
        try {
            ExceptionalClimbing.sendClimb(nullArray, nullArraySize, "V7");
            return false;
        } catch(DataFormatException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("invalid oversize array")) {
                return false;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // if the output is the original size or size + 1 the
        // test passes and returns true
        return true;
    }

    /**
     * Checks whether testFailClimb() works as expected
     *
     * @return true if method works as intended, false otherwise
     */
    public static boolean testFailClimb() throws DataFormatException {
        System.out.println("---------------------");
        System.out.println("failClimb test method");
        System.out.println("---------------------");
        String[] fail = new String[5];
        int size = 0;
        String[] fullArray = new String[]{"V2", "V3"};
        int fullArraySize = 2;
        String[] nullArray = new String[]{"V2", "V4", null, "V6", "V7"};
        int nullArraySize = 4;

        // valid input
        try {
            ExceptionalClimbing.failClimb(fail, size, "V7");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // invalid grade
        try {
            ExceptionalClimbing.failClimb(fail, size, "V8");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("V8 is not a valid grade")) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // full size array
        try {
            ExceptionalClimbing.failClimb(fullArray, fullArraySize, "V2");
            return false;
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("cannot add new value to full length 2 array")) {
                return false;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // numSend is less than 0
        try {
            ExceptionalClimbing.failClimb(fail, -1, "V4");
            return false;
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("invalid oversize array")) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        // null element array
        try {
            ExceptionalClimbing.failClimb(nullArray, nullArraySize, "V7");
            return false;
        } catch(DataFormatException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("invalid oversize array")) {
                return false;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Checks whether testGetStats() works as expected
     *
     * @return true if method works as intended, false otherwise
     */
    public static boolean testGetStats() {
        System.out.println("---------------------");
        System.out.println("getStats test method");
        System.out.println("---------------------");
        String[] send = new String[10];
        String[] fail = new String[10];
        send[0] = "V1";
        send[1] = "V2";
        send[2] = "V3";

        // valid input
        try {
           ExceptionalClimbing.getStats(send, 3, fail, 0, 2);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // negative historyLength
        try {
            ExceptionalClimbing.getStats(send, 2, fail, 0, -3);
            return false;
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            if(!(e instanceof IllegalArgumentException)) {
                return false;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // both arrays empty
        String[] emptySendArray = new String[5];
        String[] emptyFailArray = new String[5];

        try {
            ExceptionalClimbing.getStats(emptySendArray, 0, emptyFailArray,
                0, 1);
            return false;
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("no climbs provided")) {
                return false;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Checks whether testGetHistogram() works as expected
     *
     * @return true if method works as intended, false otherwise
     */
    public static boolean testGetHistogram() {
        System.out.println("------------------------");
        System.out.println("getHistogram test method");
        System.out.println("------------------------");
        String[] send = new String[] {"V3", "V2", null, null, null};
        String[] fail = new String[] {"V3", "V1", null, null, null};

        // valid input
        try {
            ExceptionalClimbing.getHistogram(send, 2, fail, 2);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // both arrays empty
        String[] emptySendArray = new String[6];
        String[] emptyFailArray = new String[6];
        try {
            ExceptionalClimbing.getHistogram(emptySendArray, 0, emptyFailArray, 0);
            return false;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if(!e.getMessage().equals("no climbs provided")) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * The method runAllTests() verifies that entire program works
     *
     */
    public static boolean runAllTests() throws DataFormatException, RuntimeException {
        System.out.println(testSendClimb() & testFailClimb() & testGetStats() & testGetHistogram());
        return testSendClimb() & testFailClimb() & testGetStats() & testGetHistogram();

    }


}

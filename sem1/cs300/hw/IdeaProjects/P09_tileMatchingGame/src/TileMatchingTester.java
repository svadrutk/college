//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tile Matching Tester
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

public class TileMatchingTester {
    public static void main(String[] args) {
        tileEqualsTester();
        tileListIteratorTester();
        tileStackTester();
        tileMatchingGameTester();
    }

    /**
     *
     * @return boolean based on if tester works
     */
    public static boolean tileEqualsTester() {
        boolean error = true;
        // same color
        Tile sameColorA = new Tile(Color.BLUE);
        Tile sameColorB = new Tile(Color.BLUE);
        if(!sameColorB.equals(sameColorA)) {
            error = false;
            System.out.println("Comparison does not work for two tiles of the same color.");
        }
        // different color
        Tile differentColorA = new Tile(Color.BLUE);
        Tile differentColorB = new Tile(Color.BLACK);
        if(differentColorB.equals(differentColorA)) {
            error = false;
            System.out.println("Comparison does not work for two tiles of different colors.");

        }
        // different objects
        Tile differentObjectA = new Tile(Color.BLUE);
        Object differentObjectB = new Object();
        if(differentObjectB.equals(differentObjectA)) {
            error = false;
            System.out.println("Comparison does not work for two different objects. ");
        }
        // object to itself
        if(!sameColorA.equals(sameColorA)) {
            error = false;
            System.out.println("Comparison does not work for comparing an object to itself.");
        }
        // object to null
        if(sameColorA.equals(null)) {
            error = false;
            System.out.println("Comparison does not work for comparing an object to a null reference.");
        }
        sameColorA = null;
        try {
            boolean a = sameColorA.equals(sameColorB);
        } catch(NullPointerException e) {

        } catch(Exception e) {
            error = false;
            System.out.println("Comparison does not work for comparing a null object to another reference.");
        }
        if(error) {
            System.out.println("Tile.equals() passed all tests.");
        }
        return error;
    }

    /**
     *
     * @return boolean based on if iterator works
     */
    public static boolean tileListIteratorTester(){
        boolean error = true;
        // initialize a list of Tiles to iterate through.
        Tile blue = new Tile(Color.BLUE);
        Tile black = new Tile(Color.BLACK);
        Tile yellow = new Tile(Color.YELLOW);
        Tile orange = new Tile(Color.ORANGE);
        // initialize the nodes.
        Node d = new Node(orange);
        Node c = new Node(yellow, d);
        Node b = new Node(black, c);
        Node a = new Node(blue, b);

        TileListIterator iterator = new TileListIterator(a);
        // check the next() method; it should not throw an exception here and should return the head.
        String s = iterator.next().toString();
        if(!s.equals("BLUE")) {
            error = false;
            System.out.println("Iterator does not return the correct value for head.");
        }
        String t = iterator.next().toString();
        if(!t.equals("BLACK")) {
            error = false;
            System.out.println("Iterator does not return the correct value for the next.");
        }
        // check the next() method and see if it returns a NoSuchElement Exception
        try {
            iterator.next();
            iterator.next();
            iterator.next();
        } catch(NoSuchElementException e) {

        } catch(Exception e) {
            error = false;
            System.out.println("Iterator does not return the correct exception.");
        }
         
        if(error) {
            System.out.println("TileListIterator passed all tests.");
        }
        return error;
    }

    /**
     *
     * @return boolean based on if stack commands work
     */
    public static boolean tileStackTester(){
        boolean error = true;
        TileStack stack = new TileStack();
        // checks isEmpty()
        if(!stack.isEmpty()) {
            error = false;
            System.out.println("isEmpty() method is incorrect for an empty stack.");
        }
        Tile a = new Tile(Color.BLACK);
        stack.push(a);
        if(stack.isEmpty()) {
            error = false;
            System.out.println("isEmpty() method is incorrect for a nonempty stack.");
        }
        // checks size
        stack.pop();
        if(stack.size() !=  0) {
            error = false;
            System.out.println("size() method is incorrect for an empty stack.");
        }
        stack.push(a);
        if(stack.size() !=  1) {
            error = false;
            System.out.println("size() method is incorrect for a nonempty stack. ");
        }
        // checks pop()
        if(!stack.pop().toString().equals("BLACK") && stack.size() != 0) {
            error = false;
            System.out.println("pop() method is incorrect. ");
        }
        // checks push() and peek()
        stack.push(a);
        if(!stack.peek().toString().equals("BLACK") && stack.size() != 1) {
            error = false;
            System.out.println("Either push() or peek() is incorrect.");
        }
        // checks iterator()
        Tile b = new Tile(Color.YELLOW);
        Tile c = new Tile(Color.ORANGE);
        stack.push(b);
        stack.push(c);
        Iterator<Tile> iterator = stack.iterator();
        iterator.next();
        if(!iterator.next().toString().equals("YELLOW")) {
            error = false;
            System.out.println("iterator() is incorrect.");
        }
        if(!iterator.next().toString().equals("BLACK")) {
            error = false;
            System.out.println("iterator() is incorrect. ");
        }

        if(error) {
            System.out.println("TileStack passed all tests.");
        }
        return error;
    }

    /**
     *
     * @return boolean based on if game works
     */
    public static boolean tileMatchingGameTester() {
        boolean error = true;
        // check if constructor is valid.
        TileMatchingGame game = new TileMatchingGame(1);
        if(game.getColumnsNumber() != 1) {
            error = false;
            System.out.println("Game has wrong amount of columns. ");
        }
        // check if restartGame and clearColumns is valid.
        game.restartGame();
        if(game.getColumnsNumber() != 1) {
            error = false;
            System.out.println("Game does not correctly clear all columns.");
        }
        // check if dropTile and toString is valid.
        Tile a = new Tile(Color.ORANGE);
        game.dropTile(a, 0);
        if(!game.toString().equals("GAME COLUMNS: " + "\n" + "0: ORANGE" + "\n")) {
            error = false;
            System.out.println("Either the toString() or dropTile() method is incorrect.");
        }
        if(error) {
            System.out.println("TileMatchingGame passed all tests. ");
        }
        return error;
    }
}

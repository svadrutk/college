//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tile Matching Game
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

public class TileMatchingGame {
    private TileStack[] columns;

    /**
     * Default constructor for game
     * @param columnCount
     */
    public TileMatchingGame(int columnCount) {
        columns = new TileStack[columnCount];
        for(int i = 0; i < columnCount; i++) {
            columns[i] = new TileStack();
        }
    }

    /**
     *
     * @return amount of columns in game
     */
    public int getColumnsNumber() {
        return columns.length;
    }

    /**
     * Push tile to specified stack
     * @param tile
     * @param index
     */
    public void dropTile(Tile tile, int index) {
        columns[index].push(tile);
    }

    /**
     * Clear specified stack
     * @param index
     */
    public void clearColumn(int index) {
        while(!columns[index].isEmpty()) {
            columns[index].pop();
        }
    }

    /**
     * Clear all stacks
     */
    public void restartGame() {
        for(int i = 0; i < getColumnsNumber(); i++) {
            clearColumn(i);
        }
    }

    /**
     *
     * @param index
     * @return String representation of each column (stack)
     */
    public java.lang.String column(int index) {
        String a = "";
        Iterator iterator = columns[index].iterator();
        if(columns[index] == null) {
            return a;
        } else {
            for(int i = 0; i < columns[index].size(); i++) {
                a = a + " " + iterator.next().toString();
            }
            return a;
        }
    }

    /**
     *
     * @return string representation of game
     */
    public java.lang.String toString() {
        String a = "GAME COLUMNS: " + "\n";
        for(int i = 0; i < getColumnsNumber(); i++) {
            a = a + i + ":" + column(i) + "\n";
        }
        return a;
    }
}

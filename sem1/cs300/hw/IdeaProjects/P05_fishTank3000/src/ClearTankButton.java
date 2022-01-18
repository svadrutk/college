//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Add new clear button
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////
/**
 * This class adds a button that clears the entire tank.
 */
public class ClearTankButton extends Button {
    /**
     * Creates a button that when clicked, clears the entire tank.
     * @param x
     * @param y
     */
    public ClearTankButton(float x, float y) {
        super("Clear", x, y);
    }

    /**
     * Clear the entire tank when the button is clicked.
     */
    public void mousePressed() {
        if(this.isMouseOver()) {
            tank.clear();
        }
    }
}

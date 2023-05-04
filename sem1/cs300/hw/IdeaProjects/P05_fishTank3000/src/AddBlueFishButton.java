//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Add Blue Fish Button
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////
/**
 * This class creates a button that adds a blue fish
 */
public class AddBlueFishButton extends Button implements TankListener {
    /**
     * Makes a button that adds blue fish
     * @param x
     * @param y
     */
    public AddBlueFishButton(float x, float y) {
        super("Add Blue", x, y);
    }

    /**
     * When the button is pressed, add a blue fish to the tank.
     */
    public void mousePressed() {
        System.out.println("Mouse has been pressed.");
        if(this.isMouseOver()) {
            tank.objects.add(new BlueFish());
        }
    }
}

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Add yellow fish button
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////
/**
 * This class creates a button that adds a yellow fish
 */
public class AddYellowFishButton extends Button implements TankListener {
    /**
     * Adds a button that adds a yellow fish when clicked
     * @param x
     * @param y
     */
    public AddYellowFishButton(float x, float y) {
        super("Add Yellow", x, y);
    }

    /**
     * When clicked, add a yellow fish.
     */
    public void mousePressed() {
        if(this.isMouseOver()) {
            tank.objects.add(new Fish(2, "yellow.png"));
        }
    }
}

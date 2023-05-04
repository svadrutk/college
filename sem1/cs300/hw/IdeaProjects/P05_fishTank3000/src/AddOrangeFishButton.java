//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Add orange fish button
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////
/**
 * This class creates a button that adds an orange fish to the tank.
 */
public class AddOrangeFishButton extends Button {
    /**
     * Add a button that adds an orange button when clicked
     * @param x
     * @param y
     */
    public AddOrangeFishButton(float x, float y) {
        super("Add Orange", x, y);
    }

    /**
     * When clicked, add an orange fish to the tank.
     */
    public void mousePressed() {
        if(this.isMouseOver()) {
            tank.objects.add(new Fish());
        }
    }
}

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 2000
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

import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class defines what a fish is.
 */
public class Fish {
    // instance variables for fish class
    private static PApplet processing; // reference to actual window of application; everything's
    // drawn on here
    private PImage image; // reference to the actual image of the fish
    private float x; // x-position of fish
    private float y; // y-position of fish
    private int speed; // swimming speed of fish
    private boolean isDragging; // checks if the fish is being dragged by the mouse
    private boolean isSwimming; // checks if the fish is moving
    private static int oldMouseX; // old x-position of fish
    private static int oldMouseY; // old y-position of fish

    // methods

    // Creates a new fish object located at a specific (x, y) position of the display window
    public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
        // sets the private instance variables to the ones passed as parameters in the constructor
        this.processing = processing;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = processing.loadImage(fishImageFileName);
    }

    // Creates a new fish object positioned at the center of the display window.
    public Fish(PApplet processing) {
        // processing PApplet object that represents the display window
        // This constructor sets the image instance field to
        // a PImage whose file name is "images" + File.separator + "orange.png"
        // Sets speed instance field to 5
        // Sets the x and y position of the fish to the center of the display window
        // The created fish won’t be dragging nor swimming.
        this.image = processing.loadImage("images" + File.separator + "orange.png");
        this.speed = 5;
        this.x = (processing.width / (float) 2);
        this.y = (processing.height / (float) 2);
        this.isDragging = false;
        this.isSwimming = false;
    }

    // Returns the image of type PImage of this fish
    public PImage getImage() {
        return this.image;
        // getter of the image instance field
    }

    // Returns the x-position of this fish in the display window
    public float getPositionX() {
        return this.x;
        // getter of the x-position of this fish
    }

    // Returns the y-position of this fish in the display window
    public float getPositionY() {
        return this.y;
        // getter of the y-position of this fish
    }

    // Moves this fish with dx and dy
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        // adds dx move to the x-position of this fish
        // adds dy move to the y-position of this fish
    }

    // Checks whether this fish is being dragged
    public boolean isDragging() {
        return this.isDragging;
        // a getter for the isDragging instance field
    }

    // Starts dragging this fish
    public void startDragging() {
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
        this.isDragging = true;
        // sets oldMouseX data field to the current x-position of the mouse
        // sets oldMouseY data field to the current y-position of the mouse
        // sets the isDragging data field of this fish to true
    }

    // Stops dragging this fish
    public void stopDragging() {
        // sets the isDragging data field of this fish to false
        this.isDragging = false;
    }

    // Draws this fish to the display window.
    // This method sets also the position of this fish to follow the moves of the
    // mouse if it is being dragged
    public void draw() {
        if (isDragging) {
            move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
            // TODO: Figure out a way to set oldMouseX and oldMouseY
            oldMouseX = processing.mouseX;
            oldMouseY = processing.mouseY;
        }
        if (isSwimming) {
            swim();
        }
        processing.image(getImage(), this.x, this.y);
        // 1. if this fish is dragging, move it with (dx, dy) to follow the moves of the mouse
        // [HINT] use the current position (processing.mouseX and processing.mouseY)
        // of the mouse with respect to the old position of the mouse to compute dx and dy moves
        // Make sure to update oldMouseX and oldMouseY after moving the fish
        // 2. draw this fish to the display window by calling processing.image() method
    }

    /**
     * Checks if the mouse is over a given fish whose reference is provided as input parameter
     *
     * @return true if the mouse is over the given fish object (i.e. over the image of the fish),
     * false otherwise
     */
    public boolean isMouseOver() {
        int fishWidth = this.getImage().width;
        int fishHeight = this.getImage().height;

        // checks if the mouse is over the provided fish
        return processing.mouseX >= this.getPositionX() - (float) fishWidth / 2
            && processing.mouseX <= this.getPositionX() + (float) fishWidth / 2
            && processing.mouseY >= this.getPositionY() - (float) fishHeight / 2
            && processing.mouseY <= this.getPositionY() + (float) fishHeight / 2;
    }

    //Starts swimming this fish
    public void startSwimming() {
        this.isDragging = false;
        this.isSwimming = true;
        // 1. stops dragging the fish
        // 2. sets the isSwimming instance field to true
    }

    // Stops swimming this fish
    public void stopSwimming() {
        this.isSwimming = false;
        // Sets the isSwimming instance field of this fish to false
    }

    // Moves horizontally the fish one speed step from left to right
    public void swim() {
        this.x  = (this.x + this.speed) % processing.width;
    }
}

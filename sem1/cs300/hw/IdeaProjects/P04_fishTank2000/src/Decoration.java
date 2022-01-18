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

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class builds the decorations in the fish tank.
 */
public class Decoration {
    private static PApplet processing;
    private PImage image;
    private float x;
    private float y;
    private boolean isDragging;
    private static int oldMouseX;
    private static int oldMouseY;

    public Decoration(PApplet processing, float x, float y, String imageFileName){
        // processing: PApplet reference to the display window of the Fish Tank
        // application
        this.processing = processing;
        this.x = x;
        this.y = y;
        this.image = processing.loadImage(imageFileName);
        // x: x-position of this decoration object
        // y: y-position of this decoration object
        // imageFileName: filename of the image to be loaded for this object
    }
    public PImage getImage() {
        return this.image;
    }
    public float getPositionX() {
        return this.x;
    }
    public float getPositionY() {
        return this.y;
    }
    public boolean isDragging() {
        return isDragging;
    }
    public void startDragging() {
        this.isDragging = true;
        this.oldMouseX  = (int)this.x;
        this.oldMouseY = (int)this.y;
    }
    public void stopDragging() {
        this.isDragging = false;
    }
    public boolean isMouseOver() {
        int decoWidth = this.getImage().width;
        int decoHeight = this.getImage().height;

        // checks if the mouse is over the provided fish
        return processing.mouseX >= this.getPositionX() - (float) decoWidth / 2
            && processing.mouseX <= this.getPositionX() + (float) decoWidth / 2
            && processing.mouseY >= this.getPositionY() - (float) decoHeight / 2
            && processing.mouseY <= this.getPositionY() + (float) decoHeight / 2;
    }
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        // adds dx move to the x-position of this fish
        // adds dy move to the y-position of this fish
    }
    public void draw() {
        if (isDragging) {
            move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
            oldMouseX = processing.mouseX;
            oldMouseY = processing.mouseY;
        }
        processing.image(getImage(), this.x, this.y);
        // 1. if this fish is dragging, move it with (dx, dy) to follow the moves of the mouse
        // [HINT] use the current position (processing.mouseX and processing.mouseY)
        // of the mouse with respect to the old position of the mouse to compute dx and dy moves
        // Make sure to update oldMouseX and oldMouseY after moving the fish
        // 2. draw this fish to the display window by calling processing.image() method
    }
}

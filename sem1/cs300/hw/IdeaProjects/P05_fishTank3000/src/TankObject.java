//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tank Object
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////

import processing.core.PImage;

import java.io.File;
/**
 * This class models any object in a fish tank.
 */
public class TankObject implements TankListener {
    protected static FishTank tank; // PApplet object which represents
    // the display window
    protected PImage image; // image of this tank object
    private float x; // x-position of this tank in the display window
    private float y; // y-position of this tank in the display window
    private boolean isDragging; // indicates whether this tank object
    // is being dragged or not
    private static int oldMouseX; // old x-position of the mouse
    private static int oldMouseY; // old y-position of the mouse

    /**
     * Sets the FishTank object to the parameter in the method.
     * @param tank
     */
    public static void setProcessing(FishTank tank) {
        TankObject.tank = tank;
    }

    /**
     * Sets the class variables to the ones in the parameter
     * @param x
     * @param y
     * @param imageFileName
     */
    public TankObject(float x, float y, String imageFileName) {
        this.image = TankObject.tank.loadImage("images" + File.separator + imageFileName);
        this.x = x;
        this.y = y;
    }

    /**
     * Moves this tank object with dx and dy
     * dx move to the x-position of this tank object
     * dy move to the y-position of this tank object
     * @param dx
     * @param dy
     */
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    // Returns the x-position of this tank object
    public float getX() {
        return this.x;
    }
    // Returns the y-position of this tank object
    public float getY() {
        return this.y;
    }
    // Sets the x-position of this object
    public void setX(float x) {
        this.x = x;
    }
    // Sets the y-position of this object
    public void setY(float y) {
        this.y = y;
    }
    // Returns the image of this tank object
    public PImage getImage() {
        return this.image;
    }
    // Getter of the isDragging field.
    // returns true if this object is being dragged, false otherwise
    public boolean isDragging() {
        return isDragging;
    }
    //Starts dragging this tank object
    public void startDragging() {
        oldMouseX  = TankObject.tank.mouseX;
        oldMouseY = TankObject.tank.mouseY;
        this.isDragging = true;
    }
    // Stops dragging this tank object
    public void stopDragging() {
        this.isDragging = false;
    }

    /**
     * @Override
     */
    public void draw() {
        if (isDragging) {
            move(TankObject.tank.mouseX - oldMouseX, TankObject.tank.mouseY - oldMouseY);
            oldMouseX = TankObject.tank.mouseX;
            oldMouseY = TankObject.tank.mouseY;
        }
        TankObject.tank.image(getImage(), this.x, this.y);
    }

    /**
     *  @Override
     */
    public void mousePressed() { startDragging(); }

    /**
     * @Override
     */
    public void mouseReleased() {stopDragging();}

    /**
     * @Override
     * @return a boolean that checks if the mouse is over the object
     */
    public boolean isMouseOver() {
        int fishWidth = this.image.width;
        int fishHeight = this.image.height;

        // checks if the mouse is over this object
        return TankObject.tank.mouseX >= x - fishWidth / 2 && TankObject.tank.mouseX <= x + fishWidth / 2
            && TankObject.tank.mouseY >= y - fishHeight / 2 && TankObject.tank.mouseY <= y + fishHeight / 2;
    }
}

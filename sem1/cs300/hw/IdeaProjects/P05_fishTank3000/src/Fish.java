//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////
/**
 * This class creates a Fish TankObject.
 */
public class Fish extends TankObject {
    private int speed;
    private boolean isSwimming;

    /**
     * makes a Fish with a speed and a certain image
     * @param speed
     * @param fishImageFileName
     * @throws IllegalArgumentException
     */
    public Fish(int speed, String fishImageFileName) throws IllegalArgumentException {
        super((float)tank.randGen.nextInt(tank.width), (float)tank.randGen.nextInt(tank.height), fishImageFileName);
        if(speed <= 0) {
            throw new IllegalArgumentException("Warning: speed cannot be negative");
        }
        else {
            this.speed = speed;
        }
    }

    /**
     * default constructor that makes a Fish with speed 5 and orange
     */
    public Fish(){
        this(5, "orange.png");
    }

    /**
     * Overrides the draw() method implemented in the parent class.
     * This method sets the position of this fish to follow the
     * mouse moves if it is dragging, calls its swim() method
     * if it is swimming, and draws it to the display window.
     * You can use a partial overriding (call draw() method of
     * the super class and adds the behavior specific to drawing a fish.
     * @Override
     */
    public void draw() {
        super.draw();
        if(isSwimming()) {
            swim();
        }
    }

    /**
     * checks if the fish is swimming
     * @return if the fish is swimming
     */
    public boolean isSwimming() { return isSwimming; }

    /**
     * Starts swimming this fish
     */
    public void startSwimming() {
        super.stopDragging();
        this.isSwimming = true;
    }
    /**
     * Stops swimming this fish
     */
    public void stopSwimming() {
        this.isSwimming = false;
    }

    /**
     * Gets the speed of this fish
     * @return speed of fish
     */
    public int speed() {
        return this.speed;
    }

    /**
     * Moves horizontally the fish one speed step from left to right.
     */
    public void swim() {
        super.setX((super.getX() + this.speed) % TankObject.tank.width);
    }
}

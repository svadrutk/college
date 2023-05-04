//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Blackfish
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////
/**
 * This class creates a black fish that moves between its two favorite destinations.
 */
public class BlackFish extends Fish {
    private TankObject source;
    private TankObject destination;

    public BlackFish(TankObject source, TankObject destination) {
        super(2, "black.png");
        this.source = source;
        this.destination = destination;
    }

    /**
     * makes one speed move towards destination
     */
    public void moveTowardsDestination() {

        float xDiff = destination.getX() - this.getX();
        float yDiff = destination.getY() - this.getY();

        int d = (int) Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
        float newX = this.getX() + (this.speed() * (destination.getX() - this.getX()))/(d);
        float newY = this.getY() + (this.speed() * (destination.getY() - this.getY()))/(d);

        this.setX(newX);
        this.setY(newY);
    }

    /**
     * returns true if this black fish is over another tank object, and false otherwise
     * @param other
     * @return a boolean checking that the black fish is over another object
     */
    public boolean isOver(TankObject other) {
        float x1 = this.getX() - (this.image.width / 2);
        float y1 = this.getY() - (this.image.height / 2);

        float x2 = this.getX() + (this.image.width / 2);
        float y2 = this.getY() + (this.image.height / 2);

        float x3 = other.getX() - (other.image.width / 2);
        float y3 = other.getY() - (other.image.height / 2);

        float x4 = other.getX() + (other.image.width / 2);
        float y4 = other.getY() + (other.image.height / 2);

        if((x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)) {
            return true;
        }
        return false;
    }

    /**
     * Overrides Fish.swim() method
     * @Override
     */
    public void swim() {
        moveTowardsDestination();  // move the fish towards its destination
        if(isOver(destination)) { // if destination is reached (meaning this fish is over its destination, switch source and destination
            TankObject tmp  = this.destination;
            this.destination = this.source;
            this.source = tmp;
        }
    }
}

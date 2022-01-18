//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank
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
// Persons:         Rishu Kare; helped me figure out that the reason my program wasn't working was
//                  because I had not initialized a random variable
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class contains all the code required to create a GUI with the ability to create, remove,
 * and drag-and-drop up to 8 fish.
 *
 * @author Svadrut Kukunooru
 *
 */
public class FishTank {
    public static void main(String[] args) {
        Utility.startApplication();
    }

    private static PApplet processing; // PApplet object that represents the graphic
    // interface of the JunglePark application
    private static PImage backgroundImage; // PImage object that represents the
    // background image
    private static Fish[] fishes; // perfect size array storing the different fish present
    // in the fish tank. These fish can be of different species.
    private static Random randGen; // Generator of random numbers

    /**
        * Callback method called each time the user presses the mouse
    */
    public static void mousePressed() {
        for(Fish fish : fishes) {
            if(fish != null)
                if (isMouseOver(fish)) {
                    fish.setDragging(true);
                    break;
                } else {
                    fish.setDragging(false);
                }
        }
    }

    /**
     * Callback method called each time the mouse is released
    */
    public static void mouseReleased() {
        for(Fish fish : fishes) {
            if(fish != null) {
                fish.setDragging(false);
            }
        }
    }

    /**
     * Checks if the mouse is over a specific Fish whose reference is provided
     * as input parameter
     *
     * @param Fish reference to a specific fish
     * @return true if the mouse is over the specific Fish object (i.e. over
     * the image of the Fish), false otherwise
     */
    public static boolean isMouseOver(Fish Fish) {
        return processing.mouseX >= Fish.getPositionX() - (Fish.getImage().width / (float) 2)
            && processing.mouseX <= Fish.getPositionX() + (Fish.getImage().width / (float) 2)
            && processing.mouseY >= Fish.getPositionY() - (Fish.getImage().height / (float) 2)
            && processing.mouseY <= Fish.getPositionY() + (Fish.getImage().height / (float) 2);
    }

    /**
     * Draws and updates the application display window.
     * This callback method called in an infinite loop.
     */
    public static void draw() {
        // Draw the background image at the center of the screen
        processing.image(backgroundImage, processing.width / (float)2, processing.height / (float)2);
        for (Fish fish : fishes) {
            if (fish != null) {
                fish.draw();
            }
        }
    }
    /**
     * Callback method called each time the user presses a key
     */
    public static void keyPressed() {
        if(processing.key == 'f' || processing.key == 'F') {
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i] == null) {
                    fishes[i] = new Fish(processing, (float)randGen.nextInt(processing.width), (float)randGen.nextInt(processing.height));
                    break;
                }
            }
        }
        if(processing.key == 'r' || processing.key == 'R') {
            for (int b = 0; b < fishes.length; b++) {
                if (fishes[b] != null && isMouseOver(fishes[b])) {
                    fishes[b] = null;
                    break;
                }
            }
        }
        if(processing.key == 's' || processing.key == 'S') {
            for(int i = 0; i < fishes.length; i++) {
                if(fishes[i] != null) {
                    fishes[i].startSwimming;
                }
            }
        }
    }

    /**
     * Defines the initial environment properties of this application
     * @param processingObj a reference to the graphic display window of this application
     */
    public static void setup(PApplet processingObj) {
        // load the image of the background
        processing = processingObj;
        randGen = new Random();
        backgroundImage = processing.loadImage("images/background.png");
        // width [resp. height]: System variable of the processing library that stores
        // the width [resp. height] of the display window.
        fishes = new Fish[8];

    }
}

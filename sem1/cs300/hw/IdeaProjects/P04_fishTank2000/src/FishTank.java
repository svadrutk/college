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
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class builds the fish tank and initializes the decorations and fishes.
 */

public class FishTank {
  private static PApplet processing; // PApplet object which represents the graphic interface
                                     // of the Fish Tank application
  private static PImage backgroundImage; // PImage object which represents the background image
  private static Fish[] fishes; // array storing the current fishes present in the tank
  private static Random randGen; // Generator of random numbers
  // circular indexed array of fish images names
  private static String[] images =
      new String[] {"orange.png", "blue.png", "yellow.png", "black.png"};
  // index of next fish image index in the circular array images
  private static int nextImageIndex;
  private static int fishSpeed;
  private static Decoration[] decorations;



  /**
   * Defines initial environment properties such as screen size and to load background images and
   * fonts as the program starts. It also initializes all data fields.
   * 
   * @param processingObj a PApplet object that represents the display window of the Fish Tank
   *                      application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj;
    backgroundImage = processing.loadImage("images" + File.separator + "background.png");
    fishes = new Fish[8];
    randGen = new Random();
    fishSpeed = 5;
    decorations = new Decoration[4];
    decorations[0] = new Decoration(processing, 430, 60, "images" +
        File.separator + "flower.png");
    decorations[1] = new Decoration(processing, 580, 470, "images" +
        File.separator +
        "log.png");
    decorations[2] = new Decoration(processing, 65, 520, "images" +
        File.separator +
        "shell.png");
    decorations[3] = new Decoration(processing, 280, 535, "images" +
        File.separator +
        "ship.png");
  }

  /**
   * Continuously draws and updates the application display window
   * 
   */
  public static void draw() {
    // clear the display window by drawing the background image
    processing.image(backgroundImage, (float) processing.width / 2,
        (float) processing.height / 2);

    // traverse the fishes array and draw each of the fish present in the tank
    for (Fish fish : fishes) {
      if (fish != null) {
        fish.draw();
      }
    }
    // traverse the decorations array and draw each of the decorations present in the tank
    for (Decoration decoration : decorations) {
      if (decoration != null) {
        decoration.draw();
      }
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // traverse the fishes array and start dragging a fish if the mouse is over it
    for (Fish fish : fishes) {
      if (fish != null && fish.isMouseOver()) {
        fish.startDragging();
        break; // only the fish at the lowest index will start dragging if there are fishes
        // overlapping
      }
    }
    for (Decoration decoration : decorations) {
      if (decoration != null && decoration.isMouseOver()) {
        decoration.startDragging();
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // traverse the fishes array and stop dragging any fish
    for (Fish fish : fishes) {
      if (fish != null)
        fish.stopDragging();
    }
    for (Decoration decoration : decorations) {
      if (decoration != null)
        decoration.stopDragging();
    }
  }



  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    if(processing.key == 'f' || processing.key == 'F') {
      for (int i = 0; i < fishes.length; i++) {
        if (fishes[i] == null) {
          System.out.println("making new fish");
          fishes[i] = new Fish(processing, (float)4.nextInt(processing.width),
              (float)randGen.nextInt(processing.height), fishSpeed,
              "images" + File.separator + images[nextImageIndex]);
              if(nextImageIndex == images.length - 1 ) {
                nextImageIndex = 0;
              }
              else {
                nextImageIndex++;
              }
          break;
        }
      }
    }
    if(processing.key == 'r' || processing.key == 'R') {
      for (int b = 0; b < fishes.length; b++) {
        if (fishes[b] != null && fishes[b].isMouseOver()) {
          fishes[b] = null;
          break;
        }
      }
    }
    if(processing.key == 's' || processing.key == 'S') {
      for (Fish fish : fishes) {
        if (fish != null) {
          fish.startSwimming();
        }
      }
    }
    if(processing.key == 'x' || processing.key == 'X') {
      for (Fish fish : fishes) {
        if (fish != null) {
          fish.stopSwimming();
        }
      }
    }
  }

  /**
   * This main method starts the application
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // starts the application
    Utility.startApplication();

  }

}

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
////////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class creates the fish tank, the objects inside it, and the actions that can be performed
 * on the objects.
 */
public class FishTank extends PApplet {
  private PImage backgroundImage; // PImage object which represents the background image
  protected ArrayList<TankListener> objects; // list storing interactive objects
  protected Random randGen; // Generator of random numbers
  // Decorations
  private TankObject flower;
  private TankObject log;
  private TankObject shell;
  private TankObject ship;

  /**
   * sets the size of this PApplet to 800 width x 600 height
   * @Override
   */
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines initial environment properties such as screen size and
   * loads the background image and fonts as the program starts.
   * It also initializes all data fields.
   * The above IS NOT a javadoc style method header!
   * @Override
   */
  public void setup() {
    // Set and display the title of the display window
    this.getSurface().setTitle("Fish Tank 3000");
    // Set the location from which images are drawn to CENTER
    this.imageMode(PApplet.CENTER);
    // Set the location from which rectangles are drawn.
    this.rectMode(PApplet.CORNERS);
    // rectMode(CORNERS) interprets the first two parameters of rect() method
    // as the location of one corner, and the third and fourth parameters as
    // the location of the opposite corner.
    // rect() method draws a rectangle to the display window

    this.focused = true; // Confirms that our Processing program is focused,
    // meaning that it is active and will accept mouse or keyboard input.

    // sets the text alignment to center
    this.textAlign(PApplet.CENTER, PApplet.CENTER);

    // load the background image and store the loaded image to backgroundImage
    // Note that you can call the loadImage() method directly (this.loadImage())
    backgroundImage = this.loadImage("images" + File.separator + "background.png");

    // create an empty array list of objects
    objects = new ArrayList<>();

    // set randGen to the reference of a new Random objects
    randGen = new Random();
    TankObject.setProcessing(this);

    // create and add decorations
    flower = new TankObject(430, 60, "flower.png");
    log = new TankObject(580, 470, "log.png");
    shell = new TankObject(65, 520, "shell.png");
    ship = new TankObject(280, 535, "ship.png");
    objects.add(flower);
    objects.add(log);
    objects.add(shell);
    objects.add(ship);

    // add black fishes
    objects.add(new BlackFish(log, flower));
    objects.add(new BlackFish(shell, flower));

    // add buttons
    Button.setProcessing((this));
    objects.add(new AddBlueFishButton(43, 16));
    objects.add(new AddYellowFishButton(215, 16));
    objects.add(new AddOrangeFishButton(129, 16));
    objects.add(new ClearTankButton(301, 16));
  }

  /**
   * Continuously draws and updates the application display window
   * @Override
   */
  public void draw() {
    // TODO clear the display window by drawing the background image
    this.image(backgroundImage, (float) this.width / 2,
        (float) this.height / 2);
    // TODO traverse the objects list and draw each of the objects to this display window
    for (TankListener object : objects) {
      if (object != null) {
        object.draw();
      }
    }

  }

  /**
   * Callback method called each time the user presses the mouse
   * @Override
   */
  public void mousePressed() {
    // TODO traverse the objects list and call mousePressed method
    // of the first object being clicked in the list
    for (TankListener object : objects) {
      if (object.isMouseOver()) {
        object.mousePressed();
        break;
        }
         // only the fish at the lowest index will start dragging if there are fishes
        // overlapping
      }
    }


  /**
   * Callback method called each time the mouse is released
   * @Override
   */
  public void mouseReleased() {
    // TODO traverse the objects list and call each object's mouseReleased() method
    for (TankListener object : objects) {
      if(object.isMouseOver()) {
        object.mouseReleased();
        break;
      }
    }
  }

  /**
   * adds an instance of TankListener passed as input to the objects arraylist
   */
  public void addObject(TankListener object) {
    objects.add(object);

  }

  /**
   * Callback method called each time the user presses a key
   * @Override
   */
  public void keyPressed() {
    if(this.key == 'O' || this.key == 'o') {
      objects.add(new Fish()); // add a default fish
    }
    if(this.key == 'Y' || this.key == 'y') {
      objects.add(new Fish(2, "yellow.png")); // add a yellow fish with speed 2
    }
    if(this.key == 'R' || this.key == 'r') {
      for(TankListener object : objects) {
        if(object.isMouseOver() && object instanceof Fish) {
          objects.remove(object); // remove a fish if the mouse is over it, and you press the R key
          break;
        }
      }
    }
    if(this.key == 's' || this.key == 'S') { // make the fish start swimming if you press S
      for(TankListener object : objects) {
        if(object instanceof Fish) {
          Fish f = (Fish) object;
          f.startSwimming();
        }
      }
    }
    if(this.key == 'x' || this.key == 'X') { // make the fish stop swimming if you press X
      for(TankListener object : objects) {
        if(object instanceof Fish) {
          Fish f = (Fish) object;
          f.stopSwimming();
        }
      }
    }
    if(this.key == 'c' || this.key == 'C') { // clear the entire tank if you press C
      clear();
    }
    if(this.key == 'b' || this.key == 'B') { // add a blue fish if you press B
      objects.add(new BlueFish());
    }
  }

  public void clear() { // clear all fishes
    objects.removeIf(object -> object instanceof Fish);
  }

  /**

   * This main method starts the application
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    PApplet.main("FishTank"); // do not add any other statement to the main method
    // The PApplet.main() method takes a String input parameter which represents
    // the name of your PApplet class.

  }

}

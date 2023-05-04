// File header comes here

/**
 * This class models a Tile of a specific color
 *
 */
/**
 * @author mouna
 *
 */
public class Tile {
  private Color color; // color of this Tile

  /**
   * Creates a Tile with a specific color
   * @param color color to be assigned to this tile
   */
  public Tile(Color color) {
    this.color = color;
  }

  /**
   * Gets the color of this tile
   * @return the color of this tile
   */
  public Color getColor() {
    return color;
  }


  /**
   * Returns a string representation of this tile
   * @return the color of this tile as a string
   */
  @Override
  public String toString() {
    return color.name();
  }

  @Override
  public boolean equals(Object other) {
    if(other == null) {
      return false;
    }
    else if(other instanceof Tile) {
      return this.getColor().equals(((Tile) other).getColor());
    }
    else {
      return false; // default return statement added to resolve compiler errors
    }
  }
}

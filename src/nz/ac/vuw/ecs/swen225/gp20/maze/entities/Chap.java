package nz.ac.vuw.ecs.swen225.gp20.maze.entities;

//importing libraries needed
import com.google.common.base.Preconditions;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.FreeTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.DoorTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

import java.awt.Point;
import java.util.*;

/**
 * Represents Chap who is the hero of the game. Chap can move, interact with entities and is controlled by the player.
 *
 * @author Vic
 */
public class Chap extends Entity {
  private Map<Key, Integer> keyInventory; //stores the objects that Chap has, keys are the key part and number of keys are the value part

  /**
   * Constructor for the Chap object.
   * @param chapsLocation Where Chap is located on the board.
   */
  public Chap(Point chapsLocation) {
    super(chapsLocation);
    keyInventory = new HashMap<>();

    //add the the possible keys
    for(Maze.Colours colour : Maze.Colours.values()) {
      keyInventory.put(new Key(colour), 0);
    }
  }



  /**
   * Checks if door can be unlocked by Chap and if so unlocks the door
   * @param location  x,y coordinates of door.
   * @param maze The level in this maze.
   */
  public void unlockDoor(Point location, Maze maze) {
    Preconditions.checkArgument(maze.getBoard()[location.x][location.y] instanceof DoorTile);

    //check if have correct key for door
    for(Key key : keyInventory.keySet()) {
      if(key.getKeyColour() == ((DoorTile)maze.getBoard()[location.x][location.y]).getDoorColour()) {
        //unlock door
        maze.getBoard()[location.x][location.y] = new FreeTile();
        //remove key
        subValue(key);
        break;
      }
    }

    //TODO add postconditions for this function, check key is removed
  }

  /**
   * Adds a key to the inventory
   * @param key Key to add to inventory.
   */
  public void addToKeyInven(Key key) {
    Preconditions.checkNotNull(key);
    //go to key and increment colour
    addValue(key);

    //TODO postconditions
  }

  /**
   * Increment value
   * @param key Key key to find in the map
   */
  private void addValue(Key key) {
    for(Key invenKey : keyInventory.keySet()) {   //TODO test this
      if(invenKey.getKeyColour() == key.getKeyColour()) {
        int value = keyInventory.get(invenKey);
        keyInventory.put(invenKey, value+1);
        break;
      }
    }
  }

  /**
   * Decrement value
   * @param key Key key to find in the map
   */
  private void subValue(Key key) {
    for(Key invenKey : keyInventory.keySet()) {   //TODO test this
      if(invenKey.getKeyColour() == key.getKeyColour()) {
        int value = keyInventory.get(invenKey);
        keyInventory.put(invenKey, value-1);
        break;
      }
    }
  }

  /**
   * Gets the key inventory from Chap.
   * @return Key inventory from Chap.
   */
  public Map<Key, Integer> getKeyInventory() {
    return Collections.unmodifiableMap(keyInventory);
  }

  @Override
  public final String toString() { return "chap"; }
}
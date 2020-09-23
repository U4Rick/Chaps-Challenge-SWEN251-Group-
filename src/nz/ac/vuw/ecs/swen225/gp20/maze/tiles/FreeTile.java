package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.AccessibleTile;

/**
 * Represents an empty tile in the level.
 */
public class FreeTile extends AccessibleTile {

  @Override
  public boolean isItem() {
    return false;
  }
}
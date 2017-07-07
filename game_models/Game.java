package game_models;
import java.util.ArrayList;

public class Game {
  ArrayList<Player> players;
  Shoe shoe;

  public Game(Shoe shoe) {
    this.shoe = shoe;
  }
}
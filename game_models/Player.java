package game_models;
import java.util.ArrayList;

public class Player {
  private String name;
  private ArrayList<Card> hand;

  public Player(String name) {
    this.name = name;
    this.hand = new ArrayList<Card>();
  }
}
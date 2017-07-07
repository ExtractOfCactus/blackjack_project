package game_models;
import java.util.ArrayList;

public class Hand {
  private ArrayList<Card> cards;

  public Hand() {
    this.cards = new ArrayList<Card>();
  }

  public int size() {
    return this.cards.size();
  }
}
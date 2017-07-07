package game_models;
import enums.*;

public class Card {
  private Rank rank;
  private Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Suit getSuit() {
    return this.suit;
  }

}
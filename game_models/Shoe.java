package game_models;
import java.util.ArrayList;
import enums.*;

public class Shoe {
  ArrayList<Card> deck;
 

  public Shoe() {
    this.deck = new ArrayList<Card>();
    this.setUpDeck();
  }

  public void setUpDeck() {
    for (Rank rank : Rank.values()) {
      for (Suit suit : Suit.values()) {
        Card card = new Card(rank, suit);
        this.deck.add(card);
      }
    }
  }

  public int size() {
    return this.deck.size();
  }
}
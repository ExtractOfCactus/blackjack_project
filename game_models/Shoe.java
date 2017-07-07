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
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card card = new Card(rank, suit);
        this.deck.add(card);
      }
    }
  }

  public int size() {
    return this.deck.size();
  }

  public Card getFirstCard() {
    return this.deck.get(0);
  } 

  public void remove(int index) {
    this.deck.remove(index);
  }

  public void newShoe() {
    this.deck.clear();
    this.setUpDeck();
  }
}
package game_models;
import java.util.ArrayList;
import enums.*;
import java.util.Collections;

public class Shoe {
  private ArrayList<Card> deck;
 

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

  public ArrayList<Card> getDeck() {
    return this.deck;
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

  public Card selectRandomCard() {
    Collections.shuffle(this.deck);
    return this.deck.get(0);
  }



}
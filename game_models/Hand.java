package game_models;
import java.util.ArrayList;

public class Hand {
  private ArrayList<Card> cards;

  public Hand() {
    this.cards = new ArrayList<Card>();
  }

  public ArrayList<Card> getCards() {
    return this.cards;
  }

  public int size() {
    return this.cards.size();
  }

  public void addCard(Card card) {
    this.cards.add(card);
  }

  public void reset() {
    this.cards = new ArrayList<Card>();
  }

  public int handTotal() {
    int total = 0;
    for (Card card : cards) {
      total += card.value();
    }
    return total;
  }
}
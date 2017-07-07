package game_models;
import java.util.ArrayList;
import java.util.Collections;

public class Dealer extends Participant {
  Shoe shoe;

  public Dealer(String name) {
    super(name);
    this.shoe = new Shoe();
  }

  public Shoe getShoe() {
    return this.shoe;
  }

  public void deal(Player player) {
    Card card = this.shoe.getFirstCard();
    player.getHand().addCard(card);
    this.shoe.remove(0);
  }

  public void deal() {
    Card card = this.shoe.getFirstCard();
    this.hand.addCard(card);
    this.shoe.remove(0);
  }
}
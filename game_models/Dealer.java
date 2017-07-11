package game_models;
import java.util.ArrayList;
import java.util.Collections;
import Behaviours.*;

public class Dealer extends Participant {
  private Shoe shoe;

  public Dealer(String name) {
    super(name);
    this.shoe = new Shoe();
    Collections.shuffle(this.shoe.getDeck());
  }

  public Shoe getShoe() {
    return this.shoe;
  }

  public void deal(Participant participant) {
    Card card = this.shoe.getFirstCard();
    participant.getHand().addCard(card);
    this.shoe.remove(0);
  }

  // public void deal() {
  //   Card card = this.shoe.getFirstCard();
  //   this.hand.addCard(card);
  //   this.shoe.remove(0);
  // }

}
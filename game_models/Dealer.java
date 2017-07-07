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

  // public void deal(Player player) {
  //   ArrayList deck = this.shoe.getDeck();
  //   Collections.shuffle(deck);

  //   Card card = this.shoe.getFirstCard();
  //   player.getHand().addCard(card);
  //   this.shoe.remove(0);
  // }
}
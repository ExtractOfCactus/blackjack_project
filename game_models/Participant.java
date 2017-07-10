package game_models;
import behaviours.*;

public class Participant implements Playable {
  protected String name;
  protected Hand hand;

  public Participant(String name) {
    this.name = name;
    this.hand = new Hand();
  }

  public String getName() {
    return this.name;
  }

  public Hand getHand() {
    return this.hand;
  }

  public int handSize() {
    return this.hand.size();
  }

  // public int handValue() {
  //   return this.hand.handTotal();
  // }

}
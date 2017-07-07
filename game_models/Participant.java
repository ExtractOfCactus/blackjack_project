package game_models;
import java.util.ArrayList;

public class Participant {
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


}
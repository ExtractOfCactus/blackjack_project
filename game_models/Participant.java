package game_models;
import java.util.ArrayList;

public class Participant {
  protected String name;
  protected ArrayList<Card> hand;

  public Participant(String name) {
    this.name = name;
    this.hand = new ArrayList<Card>();
  }

  public String getName() {
    return this.name;
  }


}
package game_models;
import java.util.ArrayList;

public class Dealer extends Participant {
  Shoe shoe;

  public Dealer(String name) {
    super(name);
    this.shoe = new Shoe();
  }

  public Shoe getShoe() {
    return this.shoe;
  }
}
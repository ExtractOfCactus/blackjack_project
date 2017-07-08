package game_models;
import java.util.ArrayList;
import enums.*;

public class Game {
  private ArrayList<Player> players;
  private Dealer dealer;

  public Game() {
    this.players = new ArrayList<Player>();
    this.dealer = new Dealer("Kev the Dealer");
    // this.setUpRankValues();
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }



  // public void startGame() {

  // }

  // public void compareHands() {

  // }
 


}
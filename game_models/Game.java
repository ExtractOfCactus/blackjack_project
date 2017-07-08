package game_models;
import java.util.ArrayList;
import enums.*;

public class Game {
  private ArrayList<Player> players;
  private Dealer dealer;

  public Game() {
    this.players = new ArrayList<Player>();
    this.dealer = new Dealer("Dealer");
    // this.setUpRankValues();
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  public void addPlayer(Player player) {
    this.players.add(player);
  }

  public Dealer getDealer() {
    return this.dealer;
  }

  public void dealRound() {
    for (Player player : players) {
      this.dealer.deal(player); 
    }
  }

  public void startGame() {
    dealRound();
    this.dealer.deal();
    dealRound();
    this.dealer.deal();
  }

  // public void compareHands() {

  // }
 


}
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

  public void initialDeal() {
    dealRound();
    this.dealer.deal();
    dealRound();
    this.dealer.deal();
  }

  // public String showPlayerCards(Player player) {

  // }

  public boolean dealerBlackjack() {
    if (this.dealer.handValue() == 21) {
      for (Player player : players) {
        if (player.handValue() == 21) {
          standOff();
        }
        else {
          playerLoses(player);
          System.out.println("Dealer has BlackJack");
        }
      }
      return true;
    }
    else {
      return false;
    }
  }

  public void blackjack(Player player) {
    System.out.println(player.getName() + " wins with BlackJack!");
  }

  public void standOff() {
    System.out.println("Both hands are equal. It's a stand off!");
  }

  public void playerWins(Player player) {
    System.out.println(player.getName() + " wins!");
  }

  public void playerLoses(Player player) {
    System.out.println(player.getName() + " loses.");
  }


  public void compareHands() {
    if (dealerBlackjack() == false) {
      for (Player player : players) {
        if (player.handValue() == 21) {
          blackjack(player);
        }
        else if (player.handValue() == this.dealer.handValue()) {
          standOff();
        }
        else if (player.handValue() > this.dealer.handValue()) {
          playerWins(player);
        }
        else if (player.handValue() < this.dealer.handValue()) {
          playerLoses(player);
        }
      }
    }
  }


  public void run() {
    Player player = new Player("Kirsty");
    addPlayer(player);
    initialDeal();
    compareHands();
  }


 // Game game = new Game();

 // game.run();


}




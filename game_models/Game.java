package game_models;
import java.util.ArrayList;
import enums.*;
import java.util.HashMap;

public class Game {
  private ArrayList<Player> players;
  private Dealer dealer;
  HashMap<Enum, Integer> rankValues; 

  public Game() {
    rankValues = new HashMap<Enum, Integer>();
    this.players = new ArrayList<Player>();
    this.dealer = new Dealer("Dealer");
    this.setUpRankValues();
  }

  public void setUpRankValues() {
    HashMap<Enum, Integer> rankValues = new HashMap<Enum, Integer>();
    rankValues.put(Rank.TWO, 2);
    rankValues.put(Rank.THREE, 3);
    rankValues.put(Rank.FOUR, 4);
    rankValues.put(Rank.FIVE, 5);
    rankValues.put(Rank.SIX, 6);
    rankValues.put(Rank.SEVEN, 7);
    rankValues.put(Rank.EIGHT, 8);
    rankValues.put(Rank.NINE, 9);
    rankValues.put(Rank.TEN, 10);
    rankValues.put(Rank.JACK, 10);
    rankValues.put(Rank.QUEEN, 10);
    rankValues.put(Rank.KING, 10);
  }

  public void oneOrEleven(Player player) {
    if (player.handValue() < 11) {
      rankValues.put(Rank.ACE, 11);
    }
    else {
      rankValues.put(Rank.ACE, 1);
    }
  }

  public void oneOrEleven() {
    if (this.dealer.handValue() < 11) {
      rankValues.put(Rank.ACE, 11);
    }
    else {
      rankValues.put(Rank.ACE, 1);
    }
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
  }

  public void showPlayerCards() {
    for (Player player : players) {
      System.out.println(player.getName() + ":");
      for (Card card : player.getHand().getCards()) {
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        System.out.println(rank + " of " + suit);
      }
      System.out.println(player.getName() + " has " + player.handValue());
      System.out.println(" ");
    }
  }

  public void showDealerCards() {
    System.out.println(dealer.getName() + ":");
    for (Card card : this.dealer.getHand().getCards()) {
      Rank rank = card.getRank();
      Suit suit = card.getSuit();
      System.out.println(rank + " of " + suit);
    }
    System.out.println(dealer.getName() + " has " + dealer.handValue());
    System.out.println(" ");
  }

  

  public boolean dealerBlackjack() {
    if (this.dealer.handValue() == 21 && dealer.handSize() == 2) {
      System.out.println("Dealer has BlackJack");
      for (Player player : players) {
        if (player.handValue() == 21 && player.handSize() == 2) {
          standOff();
        }
        else {
          playerLoses(player);
        }
      }
      return true;
    }
    else {
      return false;
    }
  }

  public void dealerFinish() {
    if (dealer.handValue() < 17) {
      dealer.deal();
      int index = dealer.handSize() - 1;
      Card card = dealer.getHand().getCards().get(index);
      Rank rank = card.getRank();
      Suit suit = card.getSuit();
      System.out.println(dealer.getName() + " draws the " + rank + " of " + suit);
      if (dealer.handValue() > 21) {
        System.out.println(dealer.getName() + " has bust!");
      }
      else {
        System.out.println(dealer.getName() + " has " + dealer.handValue());
      }
      dealerFinish();
    }
  }

  public void blackjack(Player player) {
    System.out.println(player.getName() + " wins with BlackJack!");
    player.getHand().getCards().clear();
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

  public boolean dealerBust() {
    if (dealer.handValue() > 21) {
      return true;
    }
    else {
      return false;
    }
  }


  public void compareHands() {
    if (dealerBlackjack() == true) {
      return;
    }
    for (Player player : players) {
      if (player.handValue() == 21) {
        blackjack(player);
        return;
      }
      if (dealer.handValue() < 22 && player.handValue() < 22) {
        if (player.handValue() == this.dealer.handValue()) {
          standOff();
        }
        else if (player.handValue() > this.dealer.handValue()) {
          playerWins(player);
        }
        else if (player.handValue() < this.dealer.handValue()) {
          playerLoses(player);
        }
      }
      else if (dealerBust() && (player.handValue() < 22 && player.handSize() != 0)) {
        playerWins(player);
      }
    }
    
    
  }


  public void run() {
    Player player1 = new Player("Kirsty");
    Player player2 = new Player("Glen");
    addPlayer(player1);
    addPlayer(player2);
    initialDeal();
    showPlayerCards();
    showDealerCards();
    dealerFinish();
    compareHands();
  }

}




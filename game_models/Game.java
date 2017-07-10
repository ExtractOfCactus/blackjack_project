package game_models;
import java.util.ArrayList;
import enums.*;
import behaviours.*;
import java.util.HashMap;

public class Game {
  private ArrayList<Player> players;
  private Dealer dealer;
  private HashMap<Enum, Integer> rankValues; 

  public Game() {
    rankValues = new HashMap<Enum, Integer>();
    this.players = new ArrayList<Player>();
    this.dealer = new Dealer("Dealer");
    this.setUpRankValues();
  }

  public void setUpRankValues() {
    rankValues.put(Rank.ACE, 11);
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

  // public void oneOrEleven(Player player) {
  //   for (Card card : player.getHand().getCards()) {
  //     if (card.getRank() == Rank.ACE && handValue(player) > 21) {
  //       handValue(player) -= 10;
  //     }
  //   }
  // }

  // public void oneOrEleven(Dealer dealer) {
  //   for (Card card : dealer.getHand().getCards()) {
  //     if (card.getRank == Rank.ACE && handValue(dealer) > 21) {
  //       handValue(dealer) -= 10;
  //     }
  //   }
  // }

  public Integer rankValue(Card card) {
    return rankValues.get(card.getRank());
  }

  public int handValue(Player player) {
      int total = 0;
      int aceCounter = 0;
      for (Card card : player.getHand().getCards()) {
        total += rankValue(card);
        if (card.getRank() == Rank.ACE) {
          aceCounter += 1;
        }
        if (total > 21 && aceCounter > 0) {
          total -= 10;
          aceCounter -= 1;
        }
      }
      return total;
    }

  public int handValue(Dealer dealer) {
    int total = 0;
    int aceCounter = 0;
    for (Card card : dealer.getHand().getCards()) {
      total += rankValue(card);
      if (card.getRank() == Rank.ACE) {
        aceCounter += 1;
      }
      if (total > 21 && aceCounter > 0) {
        total -= 10;
        aceCounter -= 1;
      }
    }
    return total;
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
      System.out.println(player.getName() + " has " + handValue(player));
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
    System.out.println(dealer.getName() + " has " + handValue(dealer));
    System.out.println(" ");
  }

  

  public boolean dealerBlackjack() {
    if (handValue(this.dealer) == 21 && dealer.handSize() == 2) {
      System.out.println("Dealer has BlackJack");
      for (Player player : players) {
        if (handValue(player) == 21 && player.handSize() == 2) {
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
    // int handTotal = handValue(dealer);
    if (handValue(dealer) < 17) {
      dealer.deal();
      int index = dealer.handSize() - 1;
      Card card = dealer.getHand().getCards().get(index);
      Rank rank = card.getRank();
      Suit suit = card.getSuit();
      System.out.println(dealer.getName() + " draws the " + rank + " of " + suit);
      if (handValue(dealer) > 21) {
        System.out.println(dealer.getName() + " has bust!");
      }
      else {
        System.out.println(dealer.getName() + " has " + handValue(dealer));
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
    if (handValue(dealer) > 21) {
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
      if (handValue(player) == 21) {
        blackjack(player);
      }

      if (handValue(dealer) < 22 && (handValue(player) < 22 && player.handSize() != 0)) {
        if (handValue(player) == handValue(this.dealer)) {
          standOff();
        }
        else if (handValue(player) > handValue(this.dealer)) {
          playerWins(player);
        }
        else if (handValue(player) < handValue(this.dealer)) {
          playerLoses(player);
        }
      }
      else if (dealerBust() && (handValue(player) < 22 && player.handSize() != 0)) {
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




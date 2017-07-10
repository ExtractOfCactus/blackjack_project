package game_models;
import java.util.ArrayList;
import enums.*;
import behaviours.*;
import java.util.HashMap;
import java.util.Scanner;

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

  public void populatePlayers() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a new player name or type 'play' to begin: ");
    String input = scanner.nextLine();
    while (!input.toLowerCase().equals("play") && this.players.size() < 4) {
      String name = input.substring(0,1).toUpperCase() + input.substring(1);
      Player player = new Player(name);
      addPlayer(player);
      input = scanner.nextLine();
    }
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

  public String hitOrStay(Player player) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(player.getName() + ": Would you like to take a card? (Y/N)");
    String answer = scanner.nextLine().toUpperCase();
    return answer;
  }

  public void dealAndDisplay(Player player) {
    this.dealer.deal(player);
    int index = player.handSize() - 1;
    Card card = player.getHand().getCards().get(index);
    Rank rank = card.getRank();
    Suit suit = card.getSuit();
    System.out.println(player.getName() + " draws the " + rank + " of " + suit);
  }


  public void playersPlay() {
    Scanner scanner = new Scanner(System.in);
    showDealerCards();
    for (Player player : players) {
      showPlayerCards(player);
      String answer = hitOrStay(player);
      while (answer.equals("Y") && handValue(player) < 21) {
        dealAndDisplay(player);
        if (handValue(player) > 21) {
          playerBust(player);
          break;
        }
        else {
          System.out.println(player.getName() + " has " + handValue(player));
        }
        answer = scanner.nextLine().toUpperCase();
      }
    }
  }


  public void showPlayerCards(Player player) {
      System.out.println(player.getName() + ":");
      for (Card card : player.getHand().getCards()) {
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        System.out.println(rank + " of " + suit);
      }
      System.out.println(player.getName() + " has " + handValue(player));
      System.out.println(" ");
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
          standOff(player);
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

  public void standOff(Player player) {
    System.out.println("Both hands are equal. " + player.getName() + " has a stand off!");
  }

  public void playerWins(Player player) {
    System.out.println(player.getName() + " wins!");
  }

  public void playerLoses(Player player) {
    System.out.println(player.getName() + " loses.");
  }

  public void playerBust(Player player) {
    System.out.println(player.getName() + " is bust!");
    player.getHand().getCards().clear();
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
      if (handValue(player) == 21 && player.handSize() == 2) {
        blackjack(player);
      }
      if (handValue(player) > 21) {
        playerBust(player);
      }
      if (handValue(dealer) < 22 && (handValue(player) < 22 && player.handSize() != 0)) {
        if (handValue(player) == handValue(this.dealer)) {
          standOff(player);
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
    // Player player1 = new Player("Kirsty");
    // Player player2 = new Player("Glen");
    // addPlayer(player1);
    // addPlayer(player2);
    populatePlayers();
    initialDeal();
    // showPlayerCards();
    playersPlay();
    showDealerCards();
    dealerFinish();
    compareHands();
  }

}




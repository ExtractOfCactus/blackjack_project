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
  private Viewer viewer;

  public Game(Viewer viewer) {
    this.viewer = viewer;
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
    System.out.println("Enter a new player name to join the game then type 'play' to begin: ");
    String input = scanner.nextLine();
    while (!input.toLowerCase().equals("play") && this.players.size() < 4) {
      if (input.equals("")) {
        return;
      }
      String name = input.substring(0,1).toUpperCase() + input.substring(1);
      Player player = new Player(name);
      addPlayer(player);
      System.out.println(player.getName() + " has joined the game");
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
    System.out.println(" ");
    Scanner scanner = new Scanner(System.in);
    showDealerCards();
    for (Player player : players) {
      showPlayerCards(player);
      String answer = hitOrStay(player);
      while (answer.equals("Y") && handValue(player) < 21) {
        dealAndDisplay(player);
        if (handValue(player) == 21 && player.handSize() == 2) {
          Card dealerFirstCard = dealer.getHand().getCards().get(0);
          if (rankValue(dealerFirstCard) < 10) {
            viewer.blackjack(player);
          }
        }
        if (handValue(player) == 21) {
          viewer.score(player, handValue(player));
          break;
        }
        if (handValue(player) > 21) {
          viewer.playerBust(player);
          break;
        }
        else {
          viewer.score(player, handValue(player));
        }
        answer = scanner.nextLine().toUpperCase();
      }
      System.out.println(" ");
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

  public boolean noPlayersRemaining() {
    int cardCounter = 0;
    for (Player player : players) {
      cardCounter += player.handSize();
      if (cardCounter == 0) {
        return true;
      }
    }
    return false;
  }


  public void dealerFinish() {
    if (noPlayersRemaining()) {
      viewer.allBust();
      return;
    } 
    while (handValue(dealer) < 17) {
      dealer.deal();
      int index = dealer.handSize() - 1;
      Card card = dealer.getHand().getCards().get(index);
      Rank rank = card.getRank();
      Suit suit = card.getSuit();
      viewer.showNewDealerCard(rank, suit);
      if (handValue(dealer) > 21) {
        viewer.declareDealerBust();
      }
      else {
        viewer.score(dealer, handValue(dealer));
      }
      // dealerFinish();
    }
  }


  public boolean dealerBust() {
    if (handValue(dealer) > 21) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean dealerBlackjack() {
    if (handValue(dealer) == 21 && dealer.handSize() == 2) {
      viewer.declareDealerBlackjack();
      for (Player player : players) {
        if (handValue(player) == 21 && player.handSize() == 2) {
          viewer.standOff(player);
        }
        else {
          viewer.playerLoses(player);
        }
      }
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
        viewer.blackjack(player);
      }
      if (handValue(player) > 21) {
        viewer.playerBust(player);
        player.getHand().getCards().clear();
      }
      if (handValue(dealer) < 22 && (handValue(player) < 22 && player.handSize() != 0)) {
        if (handValue(player) == handValue(dealer)) {
          viewer.standOff(player);
        }
        else if (handValue(player) > handValue(dealer)) {
          viewer.playerWins(player);
        }
        else if (handValue(player) < handValue(dealer)) {
          viewer.playerLoses(player);
        }
      }
      else if (dealerBust() && (handValue(player) < 22 && player.handSize() != 0)) {
        viewer.playerWins(player);
      }
    }
  }


  public void run() {
    populatePlayers();
    initialDeal();
    playersPlay();
    showDealerCards();
    dealerFinish();
    compareHands();
  }

}
//player blackjack during playersPlay() needs enacted. (Shows up at end. Might be fine.)
//declare win on blackjack if dealer does not start with an ace or ten? Probably not needed...
// definitely need to withdraw the option to take a card if player has blackjack



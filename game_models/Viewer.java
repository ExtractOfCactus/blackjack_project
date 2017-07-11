package game_models;
import java.util.ArrayList;
import enums.*;
import behaviours.*;
import java.util.HashMap;
import java.util.Scanner;

public class Viewer {

  public void score(Player player, int handValue) {
    System.out.println(player.getName() + " has " + handValue);
  }

  public void score(Dealer dealer, int handValue) {
    System.out.println(dealer.getName() + " has " + handValue);
  }

  public void blackjack(Player player) {
    System.out.println(player.getName() + " wins with BlackJack!");
    player.getHand().getCards().clear();
  }

  public void standOff(Player player) {
    System.out.println(player.getName() + " has a stand off by matching the dealer");
  }

  public void playerWins(Player player) {
    System.out.println(player.getName() + " wins!");
  }

  public void playerLoses(Player player) {
    System.out.println(player.getName() + " loses.");
  }

  public void playerBust(Player player) {
    System.out.println(player.getName() + " is bust and loses!");
  }

  public void declareDealerBlackjack() {
    System.out.println("Dealer has BlackJack");
  }

  public void showNewDealerCard(Rank rank, Suit suit) {
    System.out.println("Dealer draws the " + rank + " of " + suit);
  }

  public void declareDealerBust() {
    System.out.println("Dealer has bust!");
  }

  // public void allBust() {
  //   System.out.println("All players have bust!")
  // }
}
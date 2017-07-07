package game_models;
import java.util.ArrayList;

public class Shoe {
  ArrayList<Card> deck;
 

  public Shoe() {
    this.deck = new ArrayList<Card>();
    this.setupDeck();
  }
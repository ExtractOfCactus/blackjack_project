import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;
import enums.*;
// import org.mockito.*;
// import static org.mockito.Mockito.*;

public class GameTest {
  Card card1;
  Player player1;
  Game game;

  @Before
  public void before() {
    card1 = new Card(Rank.FOUR, Suit.HEARTS);
    player1 = new Player("Kirsty");
    game = new Game();
  }

  @Test
  public void startsWithNoPlayers() {
    assertEquals(0, game.getPlayers().size());
  }

  @Test
  public void canAddPlayersToGame() {
    game.addPlayer(player1);
    assertEquals(1, game.getPlayers().size());
  }

  @Test
  public void canGetDealer() {
    assertEquals("Dealer", game.getDealer().getName());
  }

  @Test
  public void canStartGame() {
    game.addPlayer(player1);
    game.initialDeal();
    Shoe result = game.getDealer().getShoe();
    assertEquals(49, result.size());
    assertEquals(2, player1.handSize());
    assertEquals(1, game.getDealer().handSize());
  }

  @Test
  public void canGetCardValue() {
    assertNotNull(game.rankValue(card1));
  }

  @Test
  public void canAddHandTotals() {
    game.addPlayer(player1);
    game.initialDeal();
    assertNotNull(game.handValue(player1));
  }

  @Test
  public void dealerCanStartWithAnAce() {
    Card card2 = new Card(Rank.ACE, Suit.HEARTS);
    Card card3 = new Card(Rank.THREE, Suit.HEARTS);
    Card card4 = new Card(Rank.ACE, Suit.CLUBS);
    Card card5 = new Card(Rank.SEVEN, Suit.HEARTS);
    Card card6 = new Card(Rank.ACE, Suit.SPADES);
    Card card7 = new Card(Rank.ACE, Suit.DIAMONDS);
    Card card8 = new Card(Rank.NINE, Suit.DIAMONDS);

    game.getDealer().getHand().addCard(card2);

    assertEquals(11, game.handValue(game.getDealer()));
    game.getDealer().getHand().addCard(card3);
    game.getDealer().getHand().addCard(card4);
    game.getDealer().getHand().addCard(card5);
    game.getDealer().getHand().addCard(card6);
    game.getDealer().getHand().addCard(card7);
    game.getDealer().getHand().addCard(card8);
    assertEquals(23, game.handValue(game.getDealer()));
  }

  // @Test
  // public void canCompareHands() {
  //   game.addPlayer(player1);
  //   game.initialDeal();
  //   assertNotNull(game.compareHands());
  //   System.out.println();
  // }
}
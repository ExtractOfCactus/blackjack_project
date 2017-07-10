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

  // @Test
  // public void canCompareHands() {
  //   game.addPlayer(player1);
  //   game.initialDeal();
  //   assertNotNull(game.compareHands());
  //   System.out.println();
  // }
}
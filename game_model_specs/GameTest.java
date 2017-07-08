import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;

public class GameTest {
  Player player1;
  Game game;

  @Before
  public void before() {
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

  // @Test
  // public void canStartGame() {

  // }

  // @Test
  // public void canCompareHands() {

  // }
}
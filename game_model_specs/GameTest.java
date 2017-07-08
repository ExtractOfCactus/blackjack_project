import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;

public class GameTest {
  Game game;

  @Before
  public void before() {
    game = new Game();
  }

  @Test
  public void startsWithNoPlayers() {
    assertEquals(0, game.getPlayers().size());
  }

  // @Test
  // public void canAddPlayersToGame() {
  //   assertEquals(1, game.getPlayers().size());
  // }

  // @Test
  // public void canStartGame() {

  // }

  // @Test
  // public void canCompareHands() {

  // }
}
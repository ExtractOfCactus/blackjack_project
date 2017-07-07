import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;

public class PlayerTest {
  Player player;

  @Before
  public void before() {
    player = new Player("Glen");
  }

  @Test
  public void playerHasName() {
    assertEquals("Glen", player.getName());
  }

  @Test
  public void playerHasHand() {
    assertEquals(0, player.getHand().size());
  }
}
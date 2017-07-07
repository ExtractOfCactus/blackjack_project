import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;
import enums.*;

public class CardTest {
  Card card;

  @Before
  public void before() {
  card = new Card(Rank.FOUR, Suit.CLUBS);
  }

  @Test
  public void canGetSuit() {
    assertEquals(Suit.CLUBS, card.getSuit());
  }

  @Test 
  public void canGetRank() {
    assertEquals(Rank.FOUR, card.getRank());
  }
}
import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;
import enums.*;
import java.util.HashMap;

public class CardTest {
  Card card;

  @Before
  public void before() {
  card = new Card(Rank.FOUR, Suit.HEARTS);
  }

  @Test
  public void canGetSuit() {
    assertEquals(Suit.HEARTS, card.getSuit());
  }

  @Test 
  public void canGetRank() {
    assertEquals(Rank.FOUR, card.getRank());
  }

}
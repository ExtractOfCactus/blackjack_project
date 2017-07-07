import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;
import enums.*;

public class HandTest {
  Hand hand;
  Card card1;
  Card card2;

  @Before
  public void before() {
    card1 = new Card(Rank.FOUR, Suit.HEARTS);
    card2 = new Card(Rank.JACK, Suit.CLUBS);
    hand = new Hand();
  }

  @Test
  public void handStartsEmpty() {
    assertEquals(0, hand.size());
  }
}
import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;
import enums.*;

public class ShoeTest {
  Shoe shoe;

  @Before
  public void before() {
    shoe = new Shoe();
  }

  @Test
  public void shoeHasCards() {
    assertEquals(52, shoe.size());
  }

  // @Test
  //   public void canGetCardByIndex() {
  //     assertEquals(Suit.CLUBS, shoe.getCardByIndex(16).getSuit());
  //   }
}
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

  @Test
  public void canGetFirstCard() {
    assertEquals(Suit.HEARTS, shoe.getFirstCard().getSuit());
  }

  @Test
  public void canRemoveCard() {
    shoe.remove(1);
    assertEquals(51, shoe.size());
  }

  @Test
  public void canResetShoe() {
    shoe.remove(1);
    shoe.remove(3);
    shoe.remove(8);
    shoe.newShoe();
    assertEquals(52, shoe.size());
  }
}
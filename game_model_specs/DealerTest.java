import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;

public class DealerTest {
  Dealer dealer;

  @Before
  public void before() {
    dealer = new Dealer("Dealer");
  }

  @Test
  public void dealerHasName() {
    assertEquals("Dealer", dealer.getName());
  }

  @Test
  public void dealerHasHand() {
    assertEquals(0, dealer.getHand().size());
  }

  @Test
  public void dealerHasShoe() {
    assertEquals(52, dealer.getShoe().size());
  }
}
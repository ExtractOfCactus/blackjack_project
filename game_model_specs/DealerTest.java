import static org.junit.Assert.*;
import org.junit.*;
import game_models.*;

public class DealerTest {
  Dealer dealer;
  Player player;

  @Before
  public void before() {
    dealer = new Dealer("Dealer");
    player = new Player("Glen");
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

  @Test
  public void dealerCanDeal() {
    Card card2 = dealer.getShoe().getDeck().get(0);
    dealer.deal(player);
    Card card1 = player.getHand().getCards().get(0);

    assertEquals(51, dealer.getShoe().size());
    assertEquals(1, player.getHand().size());
    assertEquals(card1, card2);
  }

}
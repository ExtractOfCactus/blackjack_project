package enums;

public enum Rank {
  ACE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  TEN,
  JACK,
  QUEEN,
  KING;

  private int value;

  Rank() {
    // this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}
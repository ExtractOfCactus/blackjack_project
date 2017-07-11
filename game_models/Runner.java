package game_models;
import enums.*;

public class Runner {
  public static void main(String[] args){
    Viewer viewer = new Viewer();
    Game game = new Game(viewer);
    game.run();
  }
}
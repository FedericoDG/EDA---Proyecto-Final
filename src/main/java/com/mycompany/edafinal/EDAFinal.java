package com.mycompany.edafinal;

import java.io.IOException;

public class EDAFinal {

  public static void main(String[] args) {
    Game game = new Game();

    game.playerMoveRight();
    game.playerMoveRight();
    game.playerMoveRight();
    game.playerMoveLeft();

    System.out.println("******************************************");
    System.out.println("PLAYER VALUE: " + game.getPlayerValue());
    System.out.println("TREASURE VALUE: " + game.getTreasureValue());
    System.out.println("******************************************");

    try {
      game.clearScreen();
    } catch (IOException e) {
      System.out.println(e);
    }
    game.showMap();
  }
}

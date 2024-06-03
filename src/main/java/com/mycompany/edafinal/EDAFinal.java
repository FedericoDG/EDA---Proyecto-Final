package com.mycompany.edafinal;

public class EDAFinal {

  public static void main(String[] args) {
    Game game = new Game();

    System.out.println("******************************************");
    System.out.println("PLAYER VALUE: " + game.getPlayerValue());
    System.out.println("TREASURE VALUE: " + game.getTreasureValue());
    System.out.println("******************************************");

    game.printMap();
  }
}

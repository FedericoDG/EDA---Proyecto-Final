package com.mycompany.edafinal;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.Random;

public class Game {

  public final Tree tree;
  private int playerPosition; // Posición del jugador (comienza SIEMPRE en 0)
  private final int treasurePosition; // Posición del tesoro (aleatoria entre 31 y 63, que son los nodos 'hoja')
  private boolean wrongDirection;

  public Game() {
    tree = new Tree();
    playerPosition = 0;
    // treasureIndexPosition = generateRandomNumber();
    // treasurePosition = 52;
    treasurePosition = 30;
    wrongDirection = false;

  }

  /**
   * Obtiene la posición actual del nodo donde se encuentra el jugador.
   *
   * @return Un número entero que representa la posición actual del jugador.
   */
  public int getPlayerPosition() {
    return playerPosition;
  }

  /**
   * Obtiene el valor actual del nodo donde se encuentra el jugador.
   *
   * @return Un número entero que representa el valor actual del jugador.
   */
  public int getPlayerValue() {
    return tree.getArray()[playerPosition];
  }

  /**
   * Obtiene la posición del nodo donde se encuentra el tesoro.
   *
   * @return Un número entero que representa la posición del tesoro.
   */
  public int getTreasurePosition() {
    return treasurePosition;
  }

  /**
   * Obtiene el valor del nodo donde se encuentra el tesoro.
   *
   * @return Un número entero que representa el valor del tesoro.
   */
  public int getTreasureValue() {
    return tree.getArray()[treasurePosition];
  }

  /**
   * Obtiene la posición del nodo padre del nodo que actualmente ocupa el
   * jugador.
   *
   * @return Un número entero que representa la posición del nodo padre.
   */
  public int getParentPosition() {
    return (playerPosition - 1) / 2;
  }

  /**
   * Obtiene el valor del nodo padre del nodo que actualmente ocupa el jugador.
   *
   * @return Un número entero que representa el valor del nodo padre.
   */
  public int getParentValue() {
    return tree.getArray()[(playerPosition - 1) / 2];
  }

  /**
   * Realiza un movimiento hacia la izquierda del jugador.
   *
   * @return Un número entero que representa la nueva posición del jugador
   * después de moverse.
   */
  public int playerMoveLeft() {
    int leftChildIndex = 2 * playerPosition + 1;

    if (wrongDirection) {
      System.out.println("Debería volver, no te dejaré avanzar!");
      return 666;
    }

    if (leftChildIndex <= tree.getArray().length) {
      playerPosition = leftChildIndex;

      if (playerPosition == treasurePosition) {
        System.out.println("TESORO ENCONTRADO");

        return 666;
      }

      if (getParentValue() < getTreasureValue() && getPlayerValue() < getParentValue()) {
        System.out.println("DIRECCION EQUIVOCADA");
        wrongDirection = true;
      } else {
        System.out.println("VAS BIEN");
      }
    } else {
      System.out.println("Estoy en una hoja, imposible moverme hacia abajo.");
    }

    return playerPosition;
  }

  /**
   * Realiza un movimiento hacia la derecha del jugador.
   *
   * @return Un número entero que representa la nueva posición del jugador
   * después de moverse.
   */
  public int playerMoveRight() {
    int rightChildIndex = 2 * playerPosition + 2;

    if (wrongDirection) {
      System.out.println("Debería volver, no te dejaré avanzar!");
      return 666;
    }

    if (rightChildIndex <= tree.getArray().length) {
      playerPosition = rightChildIndex;

      if (playerPosition == treasurePosition) {
        System.out.println("TESORO ENCONTRADO");

        return 99;
      }

      // 32 > 1 && 48 > 32
      if (getParentValue() > getTreasureValue() && getPlayerValue() > getParentValue()) {
        System.out.println("DIRECCION EQUIVOCADA");
        wrongDirection = true;
      } else {
        System.out.println("VAS BIEN");
      }
    } else {
      System.out.println("Estoy en una hoja, imposible moverme hacia abajo.");
    }

    return playerPosition;
  }

  /**
   * Realiza un movimiento hacia atrás del jugador.
   *
   * @return Un número entero que representa la nueva posición del jugador
   * después de moverse.
   */
  public int playerMoveBack() {
    int parentIndex = getParentPosition();

    playerPosition = parentIndex;
    wrongDirection = false;
    // Disparar función que de un "mensaje de ánimo"
    System.out.println("HAS VUELTO");

    return playerPosition;
  }

  /**
   * Genera un número aleatorio.
   *
   * @return Un número entero aleatorio entre 31 y 63.
   */
  private int generateRandomNumber() {
    Random random = new Random();
    int max = tree.getArray().length; // 63
    int min = (tree.getArray().length - 1) / 2; // 31
    int randomNumber = random.nextInt(max - min + 1) + min;

    return randomNumber;
  }

  public void showMap() {
    int[] array = tree.getArray();
    int depth = (int) (Math.log(array.length) / Math.log(2)) + 1;

    for (int level = 0; level < depth; level++) {
      int levelStartIndex = (int) Math.pow(2, level) - 1;
      int levelEndIndex = (int) Math.pow(2, level + 1) - 1;
      int levelWidth = (int) Math.pow(2, depth - level - 1);

      // Espacios iniciales
      for (int i = 0; i < levelWidth - 1; i++) {
        System.out.print("  ");
      }

      // Imprimir nodos y espacios entre ellos
      for (int i = levelStartIndex; i < levelEndIndex && i < array.length; i++) {
        if (array[i] != 0) {
          if (array[i] == getPlayerValue()) {
            System.out.printf("**");
          } else {
            System.out.printf("%2d", array[i]);
          }
        }

        for (int j = 0; j < levelWidth * 2 - 1; j++) {
          System.out.print("  ");
        }
      }

      // Salto de línea al terminar un nivel del árbol
      System.out.println();
    }
    System.out.println("Ud. está aquí: **");
  }

  public void clearScreen() throws IOException {
    try {
      Robot pressbot = new Robot();
      pressbot.keyPress(17); // Holds CTRL key.
      pressbot.keyPress(76); // Holds L key.
      pressbot.keyRelease(17); // Releases CTRL key.
      pressbot.keyRelease(76); // Releases L key.
    } catch (AWTException e) {
      System.out.println(e);
    }
  }
}

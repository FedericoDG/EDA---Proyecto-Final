package com.mycompany.edafinal;

public final class Tree {

  private final int[] array;

  public Tree() {
    array = new int[63];
    fillNodes();
  }

  /**
   * Devuelve un arreglo de enteros que representa un árbol binario.
   *
   * @return Un arreglo de enteros.
   */
  public int[] getArray() {
    return array;
  }

  /**
   * Inserta un valor en el árbol de datos.
   *
   * @param index La posición en la que se debe insertar el valor.
   * @param value El valor que se debe insertar.
   */
  private void insert(int index, int value) {
    // Nodo vacío
    if (this.array[0] == 0) {
      this.array[0] = value;

      return;
    }

    if (value < array[index]) {
      // Izquierda
      int leftChildIndex = 2 * index + 1;
      if (array[leftChildIndex] != 0 && leftChildIndex < array.length) {
        insert(leftChildIndex, value);
      } else {
        array[leftChildIndex] = value;
      }
    } else {
      // Derecha
      int rightChildIndex = 2 * index + 2;
      if (array[rightChildIndex] != 0 && rightChildIndex < array.length) {
        insert(rightChildIndex, value);
      } else {
        array[rightChildIndex] = value;
      }
    }
  }

  /**
   * Llena los nodos del árbol de datos con valores entre 1 y 63.
   */
  public void fillNodes() {
    // Lista de números en el orden específico para llenar todas las ramas del
    // árbol binario, de manera tal que el árbol quede equilibrado

    // int[] values = new int[]{16, 8, 24, 4, 12, 20, 28, 2, 6, 10, 14, 18, 22, 26, 30, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31};
    int[] values = new int[]{
      32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60,
      2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62,
      1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31,
      33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63
    };
    for (int value : values) {
      insert(0, value);
    }
  }

  /**
   * Imprime el arreglo que representa el árbol binario.
   */
  public void print() {
    System.out.print("[");
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + ", ");
    }
    System.out.print("]");
    System.out.println("");
  }
}

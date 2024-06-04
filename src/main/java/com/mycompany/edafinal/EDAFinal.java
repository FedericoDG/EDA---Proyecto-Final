package com.mycompany.edafinal;

import java.awt.AWTException;
import java.util.Scanner;

public class EDAFinal {

    public static void main(String[] args) {
        Game game = new Game();

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        try {
            game.clearScreen();
        } catch (AWTException e) {
        }

        // HACER TRAMPA Y MOSTRAR DÓNDE ESTÁ ESCONDIDO EL TESORO
        System.out.println("TESORO EN ISLA: " + game.getTreasureValue());

        do {
            System.out.println("EL TESORO DEL CAPITÁN BORRACHO - MENÚ PRINCIPAL");
            System.out.println("i. Instrucciones");
            System.out.println("m. Ver el Mapa");
            System.out.println("p. Ver la cantidad de movimientos hechos");
            System.out.println("r. Ver la ruta recorrida hasta el momento");
            System.out.println("");
            System.out.println("a. Mover a la izquierda");
            System.out.println("d. Mover a la derecha");
            System.out.println("w. Mover hacia arriba (volver)");
            System.out.println("");
            System.out.println("h. salir");

            System.out.print("Ingrese su opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "i", "I" -> {
                    try {
                        game.clearScreen();
                    } catch (AWTException e) {
                    }

                    String input;

                    System.out.println("Eres el piloto de un barco pirata. El objetivo del juego es navegar");
                    System.out.println("entre las islas hasta encontrar el tesoro del terrible Capitán Borracho.");
                    System.out.println("Puedes moverte a la derecha, izquierda y, en algunas ocaciones, ¡volver!");
                    System.out.println("Cada vez que te muevas recibirás una pista de nuestro querido Capitán.");
                    System.out.println("No te olvides de revisar el mapa para saber en qué isla te encuentras.");
                    System.out.println("");
                    System.out.println("¡Que comience la aventura!");
                    System.out.println("");
                    System.out.println("Presiona la tecla 'c' para continuar");

                    while (true) {
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("c")) {
                            try {
                                game.clearScreen();
                            } catch (AWTException e) {
                            }
                            break;
                        }
                    }
                }
                case "m", "M" -> {
                    try {
                        game.clearScreen();
                    } catch (AWTException e) {
                    }

                    String input;

                    game.showMap();

                    System.out.println("");
                    System.out.println("Presiona la tecla 'c' para continuar");

                    while (true) {
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("c")) {
                            try {
                                game.clearScreen();
                            } catch (AWTException e) {
                            }
                            break;
                        }
                    }
                }
                case "p", "P" -> {
                    try {
                        game.clearScreen();
                    } catch (AWTException e) {
                    }

                    String input;

                    System.out.println("Marinero, ¡Hasta el momento has hecho " + game.getMoves() + " movimientos!");
                    System.out.println("");
                    System.out.println("Presiona la tecla 'c' para continuar");

                    while (true) {
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("c")) {
                            try {
                                game.clearScreen();
                            } catch (AWTException e) {
                            }
                            break;
                        }
                    }
                }
                case "r", "R" -> {
                    try {
                        game.clearScreen();
                    } catch (AWTException e) {
                    }

                    String input;

                    System.out.println("El recorrido de tus huellas es: " + game.getRoad());
                    System.out.println("");
                    System.out.println("Presiona la tecla 'c' para continuar");

                    while (true) {
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("c")) {
                            try {
                                game.clearScreen();
                            } catch (AWTException e) {
                            }
                            break;
                        }
                    }
                }

                case "a", "A" -> {
                    try {
                        game.clearScreen();
                    } catch (AWTException e) {
                    }

                    String input;

                    int move = game.playerMoveLeft();
                    game.printMessage(move);

                    System.out.println("");
                    System.out.println("Presiona la tecla 'c' para continuar");

                    while (true) {
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("c")) {
                            try {
                                game.clearScreen();
                            } catch (AWTException e) {
                            }
                            break;
                        }
                    }
                }
                case "d", "D" -> {
                    try {
                        game.clearScreen();
                    } catch (AWTException e) {
                    }

                    String input;

                    int move = game.playerMoveRight();
                    game.printMessage(move);

                    System.out.println("");
                    System.out.println("Presiona la tecla 'c' para continuar");

                    while (true) {
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("c")) {
                            try {
                                game.clearScreen();
                            } catch (AWTException e) {
                            }
                            break;
                        }
                    }
                }
                case "w", "W" -> {
                    try {
                        game.clearScreen();
                    } catch (AWTException e) {
                    }

                    String input;

                    int move = game.playerMoveBack();
                    game.printMessage(move);

                    System.out.println("");
                    System.out.println("Presiona la tecla 'c' para continuar");

                    while (true) {
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("c")) {
                            try {
                                game.clearScreen();
                            } catch (AWTException e) {
                            }
                            break;
                        }
                    }
                }
                case "h", "H" ->
                    continuar = false;
            }
        } while (continuar);

        sc.close();

//    System.out.println("******************************************");
//    System.out.println("PLAYER VALUE: " + game.getPlayerValue());
//    System.out.println("TREASURE VALUE: " + game.getTreasureValue());
//    System.out.println("******************************************");
    }
}

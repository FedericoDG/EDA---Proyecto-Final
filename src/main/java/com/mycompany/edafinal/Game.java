package com.mycompany.edafinal;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    public final Tree tree;
    private int playerPosition; // Posición del jugador (comienza SIEMPRE en 0)
    private final int treasurePosition; // Posición del tesoro (aleatoria entre 15 y 31, que son los nodos 'hoja')
    private boolean wrongDirection; // Bandera que marca dirección equivocada, e impide seguir avanzando en esa dirección
    private int moves; // Cantidad de movimientos realizados (insistir en una dirección incorrecta no suma movimientos)
    private ArrayList<Integer> road; // Array con la ruta recorrida por el jugador

    public Game() {
        tree = new Tree();
        playerPosition = 0;
        treasurePosition = generateRandomNumber();
        // treasurePosition = 21; // Esconde el tesoro en la isla con el valor 13
        wrongDirection = false;
        moves = 0;
        road = new ArrayList<>();
        road.add(getPlayerValue());
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
     * Obtiene la posición del nodo padre del nodo que actualmente ocupa el jugador.
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

    public int getMoves() {
        return moves;
    }

    public ArrayList<Integer> getRoad() {
        return road;
    }

    /**
     * Realiza un movimiento hacia la izquierda del jugador.
     *
     * @return Un número entero que representa la nueva posición del jugador después de moverse.
     */
    public int playerMoveLeft() {
        int leftChildIndex = 2 * playerPosition + 1;

        if (wrongDirection) {
            return 3;
        }

        moves++;

        if (leftChildIndex <= tree.getArray().length) {
            playerPosition = leftChildIndex;

            if (playerPosition == treasurePosition) {
                road.add(getPlayerValue());
                return 5;
            }

            if (getParentValue() < getTreasureValue() && getPlayerValue() < getParentValue()) {
                wrongDirection = true;

                // pushear array el valor getParentValue()
                road.add(getPlayerValue());
                return 2;
            }
        }

        // pushear array el valor getParentValue()
        road.add(getPlayerValue());
        return 1;
    }

    /**
     * Realiza un movimiento hacia la derecha del jugador.
     *
     * @return Un número entero que representa el éxito o no del movimiento (1: Bien, 2: Mal, 3: Prohibido avanzar, 5: Tesoro encontrado)
     */
    public int playerMoveRight() {
        int rightChildIndex = 2 * playerPosition + 2;

        if (wrongDirection) {
            return 3;
        }

        moves++;

        if (rightChildIndex <= tree.getArray().length) {
            playerPosition = rightChildIndex;

            if (playerPosition == treasurePosition) {
                road.add(getPlayerValue());
                return 5;
            }

            if (getParentValue() > getTreasureValue() && getPlayerValue() > getParentValue()) {
                wrongDirection = true;
                // pushear array el valor getParentValue()
                road.add(getPlayerValue());

                return 2;
            }
        }

        // pushear array el valor getParentValue()
        road.add(getPlayerValue());
        return 1;
    }

    /**
     * Realiza un movimiento hacia atrás del jugador.
     *
     * @return El número 4 que representa que el jugador ha vuelto a la casilla anterior.
     */
    public int playerMoveBack() {
        int parentIndex = getParentPosition();

        playerPosition = parentIndex;
        wrongDirection = false;
        moves++;

        // pushear array el valor getParentValue()
        road.add(getPlayerValue());
        return 4;
    }

    /**
     * Genera un número aleatorio.
     *
     * @return Un número entero aleatorio entre 16 y 65.
     */
    private int generateRandomNumber() {
        Random random = new Random();
        int max = tree.getArray().length;
        int min = (tree.getArray().length - 1) / 2;
        int randomNumber = random.nextInt(max - min + 1) + min;

        return randomNumber;
    }

    /**
     * Imprime por pantalla el el árbol de nodos que representa al juego, indicando la posición actual del jugador
     *
     */
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
                        System.out.printf("TU");
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
        System.out.println("Ud. está aquí: TU");
    }

    /**
     * Imprime un mensaje aleatorio dependiendo del id proporcionado y de un número random generado dentro del método.
     *
     * @param id El id del mensaje a imprimir.
     */
    public void printMessage(int id) {
        Random random = new Random();
        int randomNumber = random.nextInt(5);

        switch (id) {
            case 1 -> {
                switch (randomNumber) {
                    case 0 ->
                        System.out.println("¡A la vista, bucanero! ¡El tesoro está tan cerca como mi próxima resaca!");
                    case 1 ->
                        System.out.println("¡Eso es, navegante! ¡Con una dirección así, estaré bebiendo antes del anochecer!");
                    case 2 ->
                        System.out.println("¡Buen trabajo, timonel! Si seguimos así, pronto estaré nadando en botellas.");
                    case 3 ->
                        System.out.println("¡Así se navega, corsario! ¡Sigue así y seremos los reyes del ron!");
                    case 4 ->
                        System.out.println("¡Buen ojo, corsario! ¡El oro líquido está más cerca de lo que piensas!");
                }
            }
            case 2 -> {
                switch (randomNumber) {
                    case 0 ->
                        System.out.println("¡Ay, ay, ay! ¡Estamos más perdidos que un loro en una tormenta! Mejor damos la vuelta.\"");
                    case 1 ->
                        System.out.println("¡Hemos pisado un charco! ¡Este no es el camino al botín! Volvamos antes de que la marea nos arrastre.");
                    case 2 ->
                        System.out.println(" ¡Nos hemos alejado del tesoro! Volvamos antes de que la resaca nos vuelva a tierra.");
                    case 3 ->
                        System.out.println("¡Desastre a la vista! ¡No es el camino correcto, marinero! Volvamos antes de que sea demasiado tarde.");
                    case 4 ->
                        System.out.println("¡Tierra a la vista! Pero no es la tierra del tesoro. Mejor regresemos antes de que nos confundan con ballenas varadas.");
                }
            }
            case 3 -> {
                switch (randomNumber) {
                    case 0 -> {
                        System.out.println("¡Detente ahí, grumete! ¡Ni siquiera con una botella extra deberías intentar avanzar en esa dirección! ¡Prohibido por orden del Capitán Borracho!");
                    }
                    case 1 -> {
                        System.out.println("¡Alto ahí, marinero! ¡No te atrevas a desafiar las órdenes del Capitán Borracho! ¡Incluso el loro sabe que no es por allí!");
                    }
                    case 2 -> {
                        System.out.println("¡Ningún pirata cuerdo avanzaría en esa dirección! ¡Incluso el barco se queja! ¡Escucha al Capitán Borracho y cambia de rumbo!");
                    }
                    case 3 -> {
                        System.out.println("¡Cuidado, navegante! ¡Estás en aguas peligrosas! ¡El Capitán Borracho no tolera insubordinaciones! ¡Vuelve al rumbo correcto antes de que sea demasiado tarde!");
                    }
                    case 4 -> {
                        System.out.println("¡Detente y reflexiona, marinero! ¡El Capitán Borracho ha hablado y su palabra es ley! ¡No te arriesgues a un motín!");
                    }
                }
            }
            case 4 -> {
                switch (randomNumber) {
                    case 0 -> {
                        System.out.println("¡Ánimo, timonel! Incluso los mejores piratas toman desvíos. ¡La próxima vez será nuestro momento de gloria!");
                    }
                    case 1 -> {
                        System.out.println("¡Vamos, corsario! No hay vergüenza en dar media vuelta. ¡Nos estamos acercando cada vez más al ron dorado!");
                    }
                    case 2 -> {
                        System.out.println("¡Arriba el ánimo, bucanero! Hasta el mar más tranquilo tiene sus tormentas. ¡Pero juntos, navegaremos hacia la victoria!");
                    }
                    case 3 -> {
                        System.out.println("¡No hay problema, bucanero! Cada giro equivocado nos acerca más a la aventura. ¡Sigamos adelante con renovado espíritu pirata!");
                    }
                    case 4 -> {
                        System.out.println("¡Estoy orgulloso de ti, marinero! Reconocer un error es el primer paso hacia el éxito. ¡Vamos por ese tesoro con más determinación que nunca!");
                    }
                }
            }
            case 5 -> {
                switch (randomNumber) {
                    case 0, 1, 2, 3, 4 -> {
                        System.out.println("¡Bravo, bravo, bravo, marinero! ¡Has demostrado ser el pirata más astuto de todos! Sólo usaste " + getMoves() + " movimientos...");
                        System.out.println("¡Encontrar ese tesoro de botellas de ron es una hazaña digna de los cuentos más épicos del mar!");
                        System.out.println("¡Levanta tu jarra y brinda conmigo por tu éxito! ¡Que nunca te falte el viento en las velas ni el ron en el barril!");
                        System.out.println("¡Salud, y que tu próximo viaje sea aún más legendario que este!");
                        System.out.println("El Capitán Borracho te felicita y te desea aventuras sin fin en los siete mares!");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Créditos:");
                        System.out.println("* Federico González, programador y capitán del teclado en busca del código perdido en los océanos binarios.");
                        System.out.println("* Blas Haberland, co-capitán del desarrollo y maestro del diseño, experto en esquivar bugs como un verdadero corsario esquiva cañonazos.");
                        System.out.println("Tu camino tambaleante fue: " + getRoad());
                        System.exit(0);
                    }
                }
            }
        }
    }

    /**
     * Limpia la pantalla.
     *
     * @throws java.awt.AWTException
     */
    public void clearScreen() throws AWTException {
        Robot rob = new Robot();
        try {
            rob.keyPress(KeyEvent.VK_CONTROL);
            rob.keyPress(KeyEvent.VK_L);
            rob.keyRelease(KeyEvent.VK_L);
            rob.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

}

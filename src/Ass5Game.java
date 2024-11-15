// Karam Ganaiem

import gameLogic.Game;

/**
 * The Ass5Game class contains the main method to start the Arkanoid game.
 */
public class Ass5Game {

    /**
     * The main method to start the Arkanoid game.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

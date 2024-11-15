// Karam Ganaiem

package gameLogic;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.Ball;
import geometry.Block;
import geometry.Point;
import interfaces.Collidable;
import interfaces.Sprite;
import utilities.Counter;
import utilities.Paddle;
import utilities.ScoreIndicator;
import geometry.Rectangle;


import java.awt.Color;


/**
 * The {@code Game} class represents the Arkanoid game.
 * It manages the game environment, including sprites, blocks, balls, and the paddle.
 * This class also handles game initialization and animation loop.
 */
public class Game {
    // Constants for screen dimensions
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    // Instance variables
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter blockCounter;
    private Counter ballCounter;
    private ScoreIndicator gameScore;
    private Counter gameScore2;


    /**
     * Constructs a new instance of the gameLogic.Game class.
     * Initializes the sprite collection, game environment, GUI, and sleeper.
     */
    public Game() {
        // Initialize instance variables
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.sleeper = new Sleeper();

        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.gameScore = new ScoreIndicator();

        // Show the main menu
        showMainMenu();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s The sprite to be added.
     */

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game: creates blocks, balls, and a paddle.
     */
    public void initialize() {

        BlockRemover blockRemover = new BlockRemover(this, new Counter(42));
        BallRemover ballRemover = new BallRemover(this, new Counter(3));

        // all the block edges in gray color
        Block[] borders = new Block[9];
        borders[0] = new Block(new Rectangle(new geometry.Point(0, 0), 800,
                50), Color.gray);
        borders[1] = new Block(new Rectangle(new geometry.Point(0, 25), 25,
                550), Color.gray);
        borders[2] = new Block(new Rectangle(new geometry.Point(775, 25), 25,
                550), Color.gray);
        borders[3] = new Block(new Rectangle(new geometry.Point(0, 575), 25, 25), Color.gray);
        borders[4] = new Block(new Rectangle(new Point(775, 575), 25, 25), Color.gray);
        borders[5] = new Block(new Rectangle(new geometry.Point(25, 575), 750, 25), Color.gray);

        // Screen outside borders, left and right corners
        borders[6] = new Block(new Rectangle(new geometry.Point(800, 25),
                20, 600), Color.gray);
        borders[7] = new Block(new Rectangle(new geometry.Point(-20, 25),
                20, 600), Color.gray);
        borders[8] = new Block(new Rectangle(new geometry.Point(0, 0),
                800, 25), Color.white);

        // We add it blocks game
        for (Block block : borders) {
            block.addToGame(this);
        }

        int width = 45, height = 20;
        Color[] blockColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

        for (int i = 0; i < 6; i++) {
            for (int j = i; j < 12; j++) {
                Block blov = new Block(new Rectangle(
                        new Point(235 + (j * width),
                                ((i * height)) + 125),
                        width, height), blockColors[i]);
                blov.addToGame(this);
                blov.addHitListener(blockRemover);
                blov.addHitListener(this.gameScore.getCurrentScore());
            }
        }
        // Ball2 initialization logic
        geometry.Point ballCeneter = new geometry.Point(105, 70);
        Ball firstBall = new Ball(ballCeneter, 5, Color.black, environment);
        firstBall.setVelocity(0, 4);
        firstBall.addToGame(this);

        // Ball2 initialization logic
        geometry.Point ballCeneter2 = new geometry.Point(400, 270);
        Ball secondBall = new Ball(ballCeneter2, 5, Color.black, environment);
        secondBall.setVelocity(0, 4);
        secondBall.addToGame(this);

        // Ball3 initialization logic
        geometry.Point ballCeneter3 = new geometry.Point(425, 340);
        Ball thirdBall = new Ball(ballCeneter3, 5, Color.black, environment);
        thirdBall.setVelocity(0, 4);
        thirdBall.addToGame(this);

        Block deathRegion = new Block(
                new Rectangle(
                        new geometry.Point(
                                0, 573), 800, 20),
                Color.pink);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);

        Block deathRegionExtra = new Block(new
                Rectangle(
                new geometry.Point(
                        80, 350), 45, 20), Color.pink);
        deathRegionExtra.addToGame(this);
        deathRegionExtra.addHitListener(ballRemover);

        // utilities.Paddle initialization logic
        Rectangle paddleShape = new Rectangle(
                new geometry.Point(355, 560), 90, 15);
        KeyboardSensor movement = gui.getKeyboardSensor();
        Paddle player = new Paddle(movement, paddleShape, Color.black, 5);
        player.addToGame(this);

        this.addSprite(this.gameScore);
        this.ballCounter.increase(3);
        this.blockCounter.increase(42);
    }

    /**
     * Runs the game's animation loop.
     */
    public void run() {

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, 800, 600);

            d.setColor(Color.black);
            d.fillRectangle(80, 350, 45, 20);
            d.setColor(Color.white);
            d.drawText(80, 365, "Ball Killer", 11);


            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (this.ballCounter.getValue() == 0) {
                // Close the old GUI
                this.gui.close();

                // Create a new GUI for the "Game Over" screen
                GUI newGui = new GUI("New Game Over Screen", SCREEN_WIDTH, SCREEN_HEIGHT);
                DrawSurface d2 = newGui.getDrawSurface();  // Use the new GUI for the DrawSurface
                d2.setColor(Color.WHITE);
                d2.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

                // Display the final score
                d2.setColor(Color.BLACK);
                d2.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2 - 50, "Game Over!", 32);
                d2.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2, "Final Score: " + gameScore2.getValue(), 24);

                // Show the new GUI with the final score
                newGui.show(d2);

                // Wait for 5 seconds before closing the new GUI
                long startTime2 = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime2 < 5000) {
                    // Keep the "Game Over" screen visible for 5 seconds
                }

                // Close the new GUI after 5 seconds
                newGui.close();
                return;
            }

            if (this.ballCounter.getValue() == 0) {
                // Close the old GUI
                this.gui.close();

                // Create a new GUI for the "Game Over" screen
                GUI newGui = new GUI("New Game Over Screen", SCREEN_WIDTH, SCREEN_HEIGHT);
                DrawSurface d2 = newGui.getDrawSurface();  // Use the new GUI for the DrawSurface
                d2.setColor(Color.WHITE);
                d2.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

                // Display the final score
                d2.setColor(Color.BLACK);
                d2.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2 - 50, "Game Over!", 32);
                d2.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2, "Final Score: " + gameScore2.getValue(), 24);

                // Show the new GUI with the final score
                newGui.show(d2);

                // Wait for 5 seconds before closing the new GUI
                long startTime2 = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime2 < 5000) {
                    // Keep the "Game Over" screen visible for 5 seconds
                }

                // Close the new GUI after 5 seconds
                newGui.close();
                return;
            }
        }
    }

    /**
     * Displays the main menu with the "Play" button.
     */
    private void showMainMenu() {
        // Get the draw surface
        DrawSurface d = gui.getDrawSurface();
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Display the "Play" text in the middle of the screen
        d.setColor(Color.BLACK);
        d.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2 - 50, "Welcome to Arkanoid!", 32);
        d.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2, "Press 'P' to Play", 24);

        // Show the menu
        gui.show(d);

        // Wait for the player to press 'P' to start the game
        KeyboardSensor sensor = gui.getKeyboardSensor();
        while (true) {
            if (sensor.isPressed("p")) {
                initialize();  // Initialize the game
                run();         // Run the game
                break;         // Exit the loop and start the game
            }
        }
    }

    private void showEndScreen() {
        DrawSurface d = gui.getDrawSurface();
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Display final score
        d.setColor(Color.BLACK);
        d.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2 - 50, "Game Over!", 32);
        d.drawText(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2, "Final Score: " + gameScore2.getValue(), 24);

        gui.show(d);

        // Wait for the player to close the window
        while (true) {
            if (gui.getKeyboardSensor().isPressed("q")) {  // Press 'q' to close the game
                gui.close();
                break;
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the sprite collection.
     *
     * @param s The sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Decreases the count of blocks by 1.
     */
    public void decreaseBlockCount() {
        this.blockCounter.decrease(1);
    }

    /**
     * Decreases the count of balls by 1.
     */
    public void decreaseBallCount() {
        this.ballCounter.decrease(1);
    }

    /**
     * The main method to start the Arkanoid game.
     */
    public static class Ass5Game {

        /**
         * Main method.
         *
         * @param args command line arguments
         */
        public static void main(String[] args) {
            Game game = new Game();
            game.initialize();
            game.run();
        }
    }
}

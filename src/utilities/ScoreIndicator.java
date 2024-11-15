// Karam Ganaiem

package utilities;

import biuoop.DrawSurface;
import gameLogic.Game;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The ScoreIndicator class represents a sprite that displays the current score on the game screen.
 */
public class ScoreIndicator implements Sprite {
    private ScoreTrackingListener currentScore;

    /**
     * Constructs a new ScoreIndicator with the current score initialized to zero.
     */
    public ScoreIndicator() {
        this.currentScore = new ScoreTrackingListener();
    }

    /**
     * Constructs a new ScoreIndicator with the specified score.
     *
     * @param score The score to display.
     */
    public ScoreIndicator(ScoreTrackingListener score) {
        this.currentScore = score;
    }

    /**
     * Returns the current score.
     *
     * @return The current score.
     */
    public ScoreTrackingListener getCurrentScore() {
        return this.currentScore;
    }

    /**
     * Draws the score on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(375, 20, "Score: " + this.currentScore.getValue(), 20);
    }

    /**
     * This method is empty because ScoreIndicator does not have any time-dependent behavior.
     */
    @Override
    public void timePassed() {
        // This method is empty because ScoreIndicator does not have any time-dependent behavior
    }

    /**
     * Adds the ScoreIndicator to the game as a sprite.
     *
     * @param g The game to which the ScoreIndicator is added.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}

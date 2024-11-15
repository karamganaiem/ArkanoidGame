
# Arkanoid Game

A simple implementation of the classic **Arkanoid** game in Java, using the **biuoop** library for GUI and keyboard input.

## Game Overview

Arkanoid is a tile-breaking game where you control a paddle at the bottom of the screen to bounce a ball and destroy all blocks. The game ends when the ball is lost or when all blocks are cleared. The game keeps track of your score and displays the final score on the game-over screen.

## Features

- **Main Menu**: Start a new game or quit.
- **Multiple Balls**: The game starts with three balls.
- **Blocks with Different Colors**: The blocks come in various colors and are destroyed when hit by a ball.
- **Paddle Control**: The paddle can be moved using the keyboard.
- **Score Tracker**: Your score increases as you break blocks.
- **Game Over Screen**: When all balls are lost, a "Game Over" screen is shown with the final score.

## Requirements

- Java 8 or higher
- biuoop library for GUI and keyboard input

## Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/ArkanoidGame.git
   ```

2. Navigate to the project directory:
   ```bash
   cd ArkanoidGame
   ```

3. Compile and run the program:
   ```bash
   javac -d . *.java
   java gameLogic.Game
   ```

## Controls

- **Paddle Movement**: Use the left and right arrow keys to move the paddle.
- **Start Game**: Press 'P' on the main menu to start the game.
- **Quit Game**: Press 'Q' to close the game after the game-over screen.

## Contributing

Feel free to fork the repository and make your improvements. You can submit a pull request for any changes or new features you would like to add.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

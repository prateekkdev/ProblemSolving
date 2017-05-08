/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author prateek.kesarwani
 */
public class SnakeGame {

    int boardSizeX;
    int boardSizeY;
    int snakeLength;
    BoardPosition snakeHead;

    Queue<BoardPosition> snakePath;

    public SnakeGame(int boardSizeX, int boardSizeY, BoardPosition tail, int snakeLength, Direction[] directions) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.snakeLength = snakeLength;

        // This snakeHead is before L - 1 steps.
        this.snakeHead = new BoardPosition(tail.getX(), tail.getY());

        snakePath = new LinkedList<>();

        // Now we are adding snake path for L - 1 steps(ie length)
        buildSnakePath(directions);

        // Pass direction for last movement.
        moveUntilCollision(directions[directions.length - 1]);
    }

    private void buildSnakePath(Direction[] directions) {

        BoardPosition pos = null;
        for (Direction direction : directions) {
            pos = getNextPosition(direction);
            snakePath.add(pos);
        }

        // Head becomes the front of queue(ie top position)
        snakeHead = pos;
    }

    private BoardPosition getNextPosition(Direction direction) {
        int x = snakeHead.getX();
        int y = snakeHead.getY();

        switch (direction) {
            case LEFT:
                x = x - 1;
                break;
            case RIGHT:
                x = x + 1;
                break;
            case UP:
                y = y + 1;
                break;
            case DOWN:
                y = y - 1;
                break;
        }
        return new BoardPosition(x, y);
    }

    private boolean isWallCollision(BoardPosition pos) {
        if (pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() <= boardSizeX && pos.getY() <= boardSizeY) {
            return false;
        }
        return true;
    }

    private boolean isSelfCollision(BoardPosition pos) {
        if (snakePath.contains(pos)) {
            return true;
        } else {
            return false;
        }
    }

    private void moveUntilCollision(Direction lastDirection) {

        for (int countMoves = 0; true; countMoves++) {

            BoardPosition next = getNextPosition(lastDirection);

            if (isWallCollision(next)) {
                // Print Wall
                System.out.println("WALL " + countMoves);
                return;
            }

            snakePath.remove();

            if (isSelfCollision(next)) {
                // Print Body
                System.out.println("BODY " + countMoves);
                return;
            }

            // If no collision, then move snake and continue
            snakePath.add(next);
        }
    }
}

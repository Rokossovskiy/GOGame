package com.go;

import java.awt.*;

interface IGame {
    void newGame();
    void saveGame();
    void move();
}

interface IBoardController {
    void initBoard();
}

public class Game implements IGame {
    public static final int BOARD_SIZE = 13; // размер игрового поля
    private static final double KOMI = 6.5; // Фора, то бишь количество компенсации белым
    private Board board; // Состояние доски
    private Color currentPlayer;

    // Конструктор
    public Game() {
        board = new Board(BOARD_SIZE);
        currentPlayer = Color.BLACK;
    }

    // Метод, который создает новую игру
    public void newGame() {
        board = new Board(BOARD_SIZE);
        board.clear();
        currentPlayer = Color.BLACK;
    }

    @Override
    public void saveGame() {

    }

    @Override
    public void move() {
        if (currentPlayer == Color.BLACK)
            currentPlayer = Color.WHITE;
         else
            currentPlayer = Color.BLACK;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }
}

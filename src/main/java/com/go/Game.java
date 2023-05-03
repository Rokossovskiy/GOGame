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
    private static final double KOMI = 6.5; // Фора, то бишь количество компенсации белым
    private final Board board; // Состояние доски
    private Color currentPlayer;

    // Конструктор
    public Game(Board board) {
        this.board = board;
        currentPlayer = Color.BLACK;
    }

    // Метод, который создает новую игру
    public void newGame() {
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

package com.go;

import com.go.Stone.colorStone;

interface IGame {
    void newGame();
    void changePauseState();
    void saveGame();
}

interface IBoardController {
    void initBoard();
}

public class Game implements IGame {
    public static final int BOARD_SIZE = 13; // размер игрового поля
    private static final double KOMI = 6.5; // Фора, то бишь количество компенсации белым
    private Board board; // Состояние доски
    private colorStone currentPlayer;
    private final IBoardController boardController;

    // Конструктор
    public Game(IBoardController boardController) {
        this.boardController = boardController;
        board = new Board(BOARD_SIZE);
        currentPlayer = colorStone.Black;
    }

    // Метод, который создает новую игру
    public void newGame() {
        board = new Board(BOARD_SIZE);
        currentPlayer = colorStone.Black;
    }

    @Override
    public void changePauseState() {

    }

    @Override
    public void saveGame() {

    }

    private void saveGame(String fileName) {
    }

    private void move() {

    }
}

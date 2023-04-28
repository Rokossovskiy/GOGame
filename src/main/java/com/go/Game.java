package com.go;

public class Game {
    private static final int sizeBoard = 13; // размер игрового поля
    private static final double komi = 6.5; // Фора, то бишь количество компенсации белым
    private Board board; // Состояние доски
    private Stone currentPlayer;

    // Конструктор
    public Game() {
        board = new Board();
        currentPlayer = Stone.Black;
    }

    // Метод, который создает новую игру
    private void newGame() {
        Board board = new Board();
        currentPlayer = Stone.Black;
    }

    private void saveGame(String fileName) {
    }
}

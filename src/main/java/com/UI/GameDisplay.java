package com.UI;

import com.go.Board;
import com.go.Game;
import com.go.Stone;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import static com.go.Game.BOARD_SIZE;

public class GameDisplay extends JPanel {

    private final Board board;
    private final Game game;

    //Конструктор
    public GameDisplay() {
        setPreferredSize(new Dimension(600, 600)); //Размер окна
        setBackground(new Color(226, 193, 144)); //Цвет фона
        enableEvents(AWTEvent.MOUSE_EVENT_MASK); //Считывает мышь
        board = new Board(BOARD_SIZE); // Создает игровое поле
        game = new Game();
    }

    // Создает окно по заданным параметрам, добавляет панель меню сверху
    public static void main(String[] args) {
        // Создание игры
        Game game = new Game();

        // Создание фрейма
        JFrame frame = new JFrame("StrateGO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 600;
        int height = 600;
        Dimension size = new Dimension(width, height);
        frame.getContentPane().setPreferredSize(size);
        frame.setMinimumSize(new Dimension(500, 500));

        GameDisplay gameDisplay = new GameDisplay();
        frame.add(gameDisplay);

        // Создание панели кнопок
        JPanel buttonPanel = createPanel(game, gameDisplay);
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
        gameDisplay.revalidate();
    }

    // Создание кнопок управления
    private static JPanel createPanel(Game game, GameDisplay gameDisplay) {

        JPanel grid = new JPanel(new GridLayout(2, 0, 2, 0));
        grid.setBackground(new Color(226, 193, 144));

        JButton newGame = new JButton("Новая игра");
        JButton saveGame = new JButton("Сохранить игру");
        JButton openGame = new JButton("Открыть игру");
        JButton passButton = new JButton("Пасс");
        JButton cancelButton = new JButton("Отменить ход");
        JButton loveButton = new JButton("Разработчик топ");

        //Создание функционала кнопок
        newGame.addActionListener(e -> {
            game.newGame();
            gameDisplay.repaint();
            System.out.println("Кнопка \"Новая игра\" работает.");
        });

        passButton.addActionListener(e -> {
            game.move();
            System.out.println("Кнопка \"Пасс\" работает");
        });

        grid.add(newGame);
        grid.add(saveGame);
        grid.add(openGame);
        grid.add(passButton);
        grid.add(cancelButton);
        grid.add(loveButton);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flow.add(grid);
        flow.setBackground(new Color(226, 193, 144));

        return flow;
    }

    //Рисует доску и потом камни
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int boardSize = BOARD_SIZE - 1;
        int cellSize = Math.min((getWidth() - 30 * 2) / boardSize, (getHeight() - 30 * 2) / boardSize);
        int xIndent = 30 + (getWidth() - 30 * 2 - cellSize * boardSize) / 2;
        int yIndent = 30 + (getHeight() - 30 * 2 - cellSize * boardSize) / 2;
        g.setColor(new Color(78, 87, 101));
        for (int i = 0; i <= boardSize; i++) {
            g.drawLine(xIndent + i * cellSize, yIndent, xIndent + i * cellSize, getHeight() - yIndent);
            g.drawLine(xIndent, yIndent + i * cellSize, getWidth() - xIndent, yIndent + i * cellSize);
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Stone stone = board.getPosition(i, j);
                if (stone != null) {
                    g.setColor(stone.getColor());
                    int stoneSize = (int)(cellSize * 0.9); // размер камня
                    g.fillOval(xIndent + i * cellSize - stoneSize / 2,
                            yIndent + j * cellSize - stoneSize / 2, stoneSize, stoneSize);
                }
            }
        }
    }

    //Считывает клики мыши и добавляет камень в массив
    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED) {
            int boardSize = BOARD_SIZE - 1;
            int cellSize = Math.min((getWidth() - 30 * 2) / boardSize, (getHeight() - 30 * 2) / boardSize);
            int xIndent = 30 + (getWidth() - 30 * 2 - cellSize * boardSize) / 2;
            int yIndent = 30 + (getHeight() - 30 * 2 - cellSize * boardSize) / 2;
            if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                int i = (int) ((float) (x - xIndent + cellSize / 2) / cellSize);
                int j = (int) ((float) (y - yIndent + cellSize / 2) / cellSize);
                if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE) {
                    Stone stone = new Stone(game.getCurrentPlayer(), i, j);
                    if (board.getPosition(i, j) == null) game.move();
                    board.addStone(stone);
                    this.repaint(xIndent + i * cellSize - cellSize / 2, yIndent + j * cellSize - cellSize / 2, cellSize, cellSize);
                }
            }
        }
    }
}
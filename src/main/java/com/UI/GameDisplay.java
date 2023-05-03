package com.UI;

import com.go.Board;
import com.go.Game;

import javax.swing.*;
import java.awt.*;

public class GameDisplay {

    private final static int BOARD_SIZE = 13;

    public static void main(String[] args) {
        Board board = new Board(BOARD_SIZE); // Создает игровое поле
        Game game = new Game(board);

        JFrame frame = new JFrame("StrateGO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(DisplayConfig.WINDOW_SIZE);
        frame.setMinimumSize(DisplayConfig.MIN_WINDOW_SIZE);

        GamePanel gamePanel = new GamePanel(board, game);
        frame.add(gamePanel);

        GameButtonsControlPanel buttons = new GameButtonsControlPanel(new GridLayout(2, 0, 2, 0));
        buttons.addNewGameListener(gamePanel);
        GameButtonsControlContainerPanel buttonsContainer = new GameButtonsControlContainerPanel(new FlowLayout(FlowLayout.CENTER), buttons);
        frame.getContentPane().add(buttonsContainer, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
        gamePanel.revalidate();
    }
}

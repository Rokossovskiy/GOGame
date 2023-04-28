package com.io;

import javax.swing.*;

public class GameDisplay {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}

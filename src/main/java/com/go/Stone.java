package com.go;

import java.awt.*;

public class Stone {
    private final int x;
    private final int y;
    private final Color color;

    public enum colorStone {White, Black}

    public Stone(Color color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

package com.xsa.chessgame.traing_model;

import java.awt.Color;

public enum PieceColor {//枚举

    BLACK(Color.BLACK), WHITE(Color.WHITE);

    private final Color color;

    PieceColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isWhite() {
        return name().charAt(0) != (char) 66;
    }

    @Override
    public String toString() {
        return name().charAt(0) == (char) 66 ? "Black" : "White";
    }
}

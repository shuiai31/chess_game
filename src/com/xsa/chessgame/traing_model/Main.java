package com.xsa.chessgame.traing_model;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args){
        Board board = new Board();
        JFrame f = new JFrame("chessBoard");

        f.getContentPane().add(board);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.pack();
        f.setLocationByPlatform(true);
        f.setVisible(true);
    }


}

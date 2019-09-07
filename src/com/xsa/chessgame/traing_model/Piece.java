package com.xsa.chessgame.traing_model;

import java.awt.Color;
import java.util.Collection;
import javax.swing.ImageIcon;

//
public abstract class Piece {//this is abtract class

    private final PieceType pieceType;
    private final PieceColor pieceColor;
    private Square square;
    private boolean moved;

    public Piece(PieceColor color, PieceType type) {
        pieceColor = color;
        pieceType = type;
        moved = false;
    }
    
    public PieceColor getPieceColor() {
        return pieceColor;
    }
    
    public void setMoved() {
        moved = true;
    }

    public int getTypeNumber() {
        return pieceType.getTypeNumber();
    }

    public String getColorString() {
        return pieceColor.toString();
    }

    public String getType() {
        return pieceType.toString();
    }

    public Square getSquare() {
        return square;
    }

    public boolean isWhite() {
        return pieceColor.isWhite();
    }


    public boolean isOpponent(Piece piece) {
        return piece != null && isWhite() != piece.isWhite();
    }



    public abstract Collection<Square> getPossibleMoves();//not sure how all the possible moves. so defined as abstract method, to solve parent class method's unsure.
//    抽象方法在父类中不能事故先，所以没有函数体。 但在后续继承时，要具体实现此方法。

    public abstract Collection<Square> generatePossibleMoves();

    public ImageIcon getIcon() {
        String path = "/com/xsa/chessgame/res/" + getColorString() + " " + getType().substring(0, 1) + ".png";
        return new ImageIcon(getClass().getResource(path));
    }

    public void putPieceOnSquareFirstTime(Square square) {
        this.square = square;
        this.square.setPiece(this);
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return pieceColor.toString().substring(0, 1) + " " + pieceType.toString().substring(0, 1);
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public void printPossibleMoves() {
        generatePossibleMoves();
        getPossibleMoves().stream().forEach((_item) -> {
            if (isOpponent(_item.getPiece())) {
                _item.setBackground(Color.RED);
            } else {
                _item.setBackground(Color.GREEN);
            }
        });
    }

}

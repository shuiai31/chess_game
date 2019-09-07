package com.xsa.chessgame.traing_model;

import java.util.ArrayList;
import java.util.Collection;

class Rook extends Piece {

    Collection<Square> possibleMoves;

    public Rook(PieceColor color, PieceType type) {
        super(color, type);
        possibleMoves = new ArrayList<>();
    }

    @Override
    public Collection<Square> generatePossibleMoves() {
        int row = super.getSquare().ROW;
        int column = super.getSquare().COLUMN;
        possibleMoves.clear();
        //getting all the possible moves in the up
        for (int i = row + 1; i < Board.SIZE; i++) {
            Square square = super.getSquare().getBoardSquare(i, column);
            if (square.getPiece() == null) {
                possibleMoves.add(square);
            } else if (isOpponent(square.getPiece())) {
                possibleMoves.add(square);
                break;
            } else {
                break;
            }
        }
        //getting all the possible moves in the down
        for (int i = row - 1; i > -1; i--) {
            Square square = super.getSquare().getBoardSquare(i, column);
            if (square.getPiece() == null) {
                possibleMoves.add(square);
            } else if (isOpponent(square.getPiece())) {
                possibleMoves.add(square);
                break;
            } else {
                break;
            }
        }
        //getting all the possible moves to the right
        for (int i = column + 1; i < Board.SIZE; i++) {
            Square square = super.getSquare().getBoardSquare(row, i);
            if (square.getPiece() == null) {
                possibleMoves.add(square);
            } else if (isOpponent(square.getPiece())) {
                possibleMoves.add(square);
                break;
            } else {
                break;
            }
        }
        //getting all the possible moves to the left
        for (int i = column - 1; i > -1; i--) {
            Square square = super.getSquare().getBoardSquare(row, i);
            if (square.getPiece() == null) {
                possibleMoves.add(square);
            } else if (isOpponent(square.getPiece())) {
                possibleMoves.add(square);
                break;
            } else {
                break;
            }
        }
        return possibleMoves;
    }

    @Override
    public Collection<Square> getPossibleMoves() {
        return possibleMoves;
    }

}

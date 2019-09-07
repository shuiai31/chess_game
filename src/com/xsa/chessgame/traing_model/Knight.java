
package com.xsa.chessgame.traing_model;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;


class Knight extends Piece {

    Collection<Square> possibleMoves;

    public Knight(PieceColor color, PieceType type) {
        super(color, type);
        possibleMoves = new ArrayList<>();
    }

    @Override
    public ImageIcon getIcon() {
        String path = "/com/xsa/chessgame/res/" + getColorString() + " N.png";
        return new ImageIcon(getClass().getResource(path));
    }

    @Override
    public String toString() {
        return super.getColorString().substring(0, 1) + " N";
    }

    @Override
    public Collection<Square> generatePossibleMoves() {
        possibleMoves.clear();
        int[][] offsets = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
        };
        for (int[] o : offsets) {
            Square square = super.getSquare().neighbour(o[0], o[1]);
            if (square != null && (square.getPiece() == null || isOpponent(square.getPiece()))) {
                possibleMoves.add(square);
            }
        }
        return possibleMoves;
    }

    @Override
    public Collection<Square> getPossibleMoves() {
        return possibleMoves;
    }
}

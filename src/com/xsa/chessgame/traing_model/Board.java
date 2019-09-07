package com.xsa.chessgame.traing_model;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


//get the square array(8x8) and whitePieces blackPieces
//also get the attribute create new chess pieces

public final class Board extends JPanel {

    private ArrayList<Piece> pieces;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private Square[][] board;
    private Square selectedSquare;
    private JPanel boardPanel;
    private boolean turn;
    private boolean check;
    private int reminder;
    public static final int SIZE = 8;
    private static final int GAP = 5;
    private King whiteKingPiece;
    private King blackKingPiece;

    public Board() {
        create();
    }

    private JPanel createFilePanel() {
        JPanel filePanel = new JPanel(new GridLayout(1, 0));
        for (int i = 0; i < SIZE; i++) {
            char fileChar = (char) ('A' + i);
            filePanel.add(new JLabel(String.valueOf(fileChar), SwingConstants.CENTER));
        }
        return filePanel;
    }

    public static String printRow(int row) {
        return String.valueOf(SIZE - row);
    }

    public static String printColumn(int column) {
        return String.valueOf((char) ('A' + column));
    }

    private JPanel createRankPanel() {
        JPanel rankPanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < SIZE; i++) {
            int row = SIZE - i;
            rankPanel.add(new JLabel(String.valueOf(row)));
        }
        return rankPanel;
    }

    public void create() {
        removeAll();
        setLayout(new GridBagLayout());
        boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        pieces = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        turn = false;
        check = false;
        reminder = 0;
        board = new Square[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                board[row][column] = new Square(row, column, this);
                boardPanel.add(board[row][column]);
            }
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;//X=0
        gbc.gridy = 1;//Y=1
        gbc.gridwidth = 1;//row occupied one cell grid
        gbc.gridheight = 1;//column occupied one cell grid
        gbc.weightx = 0.0;//when window expired bigger, length not change
        gbc.weighty = 0.0;//when window expired bigger, height not change
        gbc.fill = GridBagConstraints.BOTH;//when grid have space, filling space
        gbc.anchor = GridBagConstraints.WEST;//when component smaller than space, letting component be in West
        gbc.insets = new Insets(0, 2 * GAP, 0, 2 * GAP);//distance between components
        add(createRankPanel(), gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(createRankPanel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(GAP, 0, GAP, 0);
        add(createFilePanel(), gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(createFilePanel(), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(GAP, 0, GAP, GAP);
        JButton newGame = new JButton("Starting the New Game!!");
        newGame.addActionListener((ActionEvent e) -> {
            create();
        });

        add(newGame, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(boardPanel, gbc);
        add(boardPanel,gbc);
        createStandardPieceSet();
    }


    public void createStandardPieceSet() {
        PieceColor whitePieceColor = PieceColor.WHITE;
        Piece[] whitePiecesFirstRow = new Piece[]{
            PieceType.ROOK.create(whitePieceColor),
            PieceType.KNIGHT.create(whitePieceColor),
            PieceType.BISHOP.create(whitePieceColor),
            PieceType.QUEEN.create(whitePieceColor),
            PieceType.KING.create(whitePieceColor),
            PieceType.BISHOP.create(whitePieceColor),
            PieceType.KNIGHT.create(whitePieceColor),
            PieceType.ROOK.create(whitePieceColor)};
        Piece[] whitePiecesSecondRow = new Piece[SIZE];
        for (int i = 0; i < whitePiecesSecondRow.length; i++) {
            whitePiecesSecondRow[i] = PieceType.PAWN.create(whitePieceColor);//create PAWN for whitePiece in second row
        }
        int count = 0;
        for (Piece piece : whitePiecesFirstRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[7][count]);
                pieces.add(piece);
                whitePieces.add(piece);
                if (piece.isKing()) {
                    whiteKingPiece = (King) piece;
                }
                count++;
            }
        }
        count = 0;
        for (Piece piece : whitePiecesSecondRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[6][count]);
                pieces.add(piece);
                whitePieces.add(piece);
                count++;
            }
        }
        PieceColor blackPieceColor = PieceColor.BLACK;
        Piece[] blackPiecesFirstRow = new Piece[]{
            PieceType.ROOK.create(blackPieceColor),
            PieceType.KNIGHT.create(blackPieceColor),
            PieceType.BISHOP.create(blackPieceColor),
            PieceType.QUEEN.create(blackPieceColor),
            PieceType.KING.create(blackPieceColor),
            PieceType.BISHOP.create(blackPieceColor),
            PieceType.KNIGHT.create(blackPieceColor),
            PieceType.ROOK.create(blackPieceColor)};
        Piece[] blackPiecesSecondRow = new Piece[SIZE];
        for (int i = 0; i < blackPiecesSecondRow.length; i++) {
            blackPiecesSecondRow[i] = PieceType.PAWN.create(blackPieceColor);
        }
        count = 0;
        for (Piece piece : blackPiecesFirstRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[0][count]);
                pieces.add(piece);
                blackPieces.add(piece);
                if (piece.isKing()) {
                    blackKingPiece = (King) piece;
                }
                count++;
            }
        }
        count = 0;
        for (Piece piece : blackPiecesSecondRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[1][count]);
                pieces.add(piece);
                blackPieces.add(piece);
                count++;
            }
        }
    }

    public void resetReminder() {
        reminder = 0;
    }

    public int getReminder() {
        return reminder++;
    }

    public boolean getTurn() {
        return turn;
    }

    public void move(Square from, Square to) {
        to.movePiece(from.getPiece());
        from.removePiece();
        turn = !turn;
    }

    public Square getSquare(int row, int column) {
        Square square = null;
        if (row < SIZE && column < SIZE && row >= 0 && column >= 0) {
            square = board[row][column];
        }
        return square;
    }

    public boolean kingInCheck() {
        if (turn) {
            return blackKingPiece.inCheck;
        } else {
            return whiteKingPiece.inCheck;
        }
    }

    public Square getSelected() {
        return selectedSquare;
    }

    public void setSelected(Square square) {
        selectedSquare = square;
    }

    public void deselect() {
        if (selectedSquare != null) {
            selectedSquare.deselect();
            for (Square[] squares : board) {
                for (Square square : squares) {
                    square.setBackground(square.getColor());
                }
            }
        }
    }

}

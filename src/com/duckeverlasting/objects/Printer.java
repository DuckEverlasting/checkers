package com.duckeverlasting.objects;

import java.util.ArrayList;

public class Printer {
    private final Game  game;

    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_RED = "\u001b[31;1m";
    private static final String ANSI_WHITE = "\u001b[37m";
    private static final String ANSI_BLACK = "\u001b[30m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001b[40m";
    private static final String ANSI_GREY_BACKGROUND = "\u001b[0m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_UNDERLINE = "\u001B[4m";

    public Printer(Game game) {
        this.game = game;
    }

    public void print(ArrayList<Integer> highlights) {
        int[] gameBoard = game.getgameBoard();
        String output = "\n\n\n\n\n\n\n\n\n\n\n" + ANSI_BLACK_BACKGROUND + ANSI_WHITE + "  ________________________________  "
                + ANSI_RESET + "\n";
        for (int i = 0; i < gameBoard.length; i++) {
            int id = gameBoard[i];

            if (i % 4 == 0) {
                output += ANSI_BLACK_BACKGROUND + ANSI_WHITE + " |";
            }

            if (i % 8 < 4) {
                output += ANSI_GREY_BACKGROUND + "    ";
            }

            if (id == -1) {
                output += ANSI_BLACK_BACKGROUND + "    ";
            } else {
                GamePiece gamePiece = game.getGamePiece(id);
                int player = gamePiece.getPlayer();
                String idString = id < 10 ? "0" + id : "" + id;
                String textColor = player == 0
                    ? ANSI_RED
                    : ANSI_WHITE;
                if (highlights.contains(i)) {
                    textColor += player == 0 ? ANSI_YELLOW_BACKGROUND : ANSI_BLACK + ANSI_YELLOW_BACKGROUND;
                }
                if (gamePiece.isKing()) {
                    textColor += ANSI_UNDERLINE;
                }
                output += ANSI_BLACK_BACKGROUND + textColor + " " + idString + " ";
            }

            if (i % 8 >= 4) {
                output += ANSI_GREY_BACKGROUND + "    ";
            }

            if (i % 4 == 3) {
                output += ANSI_BLACK_BACKGROUND + ANSI_WHITE + "| " + ANSI_RESET + "\n";
            }
        }
        output += ANSI_BLACK_BACKGROUND + ANSI_WHITE
                + "  ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅   " + ANSI_RESET + "\n";
        System.out.println(output);
    }

    public void print() {
        print(new ArrayList<Integer>());
    }
}
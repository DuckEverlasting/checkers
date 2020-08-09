package com.duckeverlasting.objects;

public class Printer
{
    private Game game;
    
    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_RED = "\u001b[31;1m";
    private static final String ANSI_WHITE = "\u001b[37m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001b[40m";
    private static final String ANSI_GREY_BACKGROUND = "\u001b[0m";

    public Printer(Game game)
    {
        this.game = game;
    }

    public void print()
    {
        int[] gameBoard = game.getgameBoard();
        GamePiece[] gamePieces = game.getGamePieces();
        String output = "\n\n\n" + ANSI_BLACK_BACKGROUND + ANSI_WHITE + "  ________________________________  " + ANSI_RESET + "\n";
        for (int i = 0; i < gameBoard.length; i++)
        {
            int id = gameBoard[i];

            if (i % 4 == 0)
            {
                output += ANSI_BLACK_BACKGROUND + ANSI_WHITE + " |";
            }

            if (i % 8 < 4)
            {
                output += ANSI_GREY_BACKGROUND + "    ";
            }

            if (id == -1)
            {
                output += ANSI_BLACK_BACKGROUND + "    ";
            } else {
                String idString = id < 10 ? "0" + id : "" + id;
                if (gamePieces[id].getPlayer() == 0) 
                {
                    output += ANSI_BLACK_BACKGROUND + ANSI_RED + " " + idString + " ";
                } 
                else {
                    output += ANSI_BLACK_BACKGROUND + ANSI_WHITE + " " + idString + " ";
                }
            }

            if (i % 8 >= 4)
            {
                output += ANSI_GREY_BACKGROUND + "    ";
            }

            if (i % 4 == 3)
            {
                output += ANSI_BLACK_BACKGROUND + ANSI_WHITE + "| " + ANSI_RESET + "\n";
            }
        }
        output += ANSI_BLACK_BACKGROUND + ANSI_WHITE + "  ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅   " + ANSI_RESET + "\n";
        System.out.println(output);
    }
}
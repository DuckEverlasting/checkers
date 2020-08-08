package com.duckeverlasting.objects;

public class Printer
{
    private GameBoard gameBoard;

    public Printer(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    public void print()
    {
        int[] state = gameBoard.getState();
        GamePiece[] gamePieces = gameBoard.getGamePieces();
        String output = "-------------------\n";
        for (int i = 0; i < state.length; i++)
        {
            if (i % 4 == 0)
            {
                output += "| ";
            }

            if (i % 8 < 4)
            {
                output += "  ";
            }

            if (state[i] == -1)
            {
                output += "  ";
            } else if (gamePieces[state[i]].getPlayer() == 0)
            {
                output += "X ";
            } else {
                output += "0 ";
            }

            if (i % 8 >= 4)
            {
                output += "  ";
            }

            if (i % 4 == 3)
            {
                output += "|\n";
            }
        }
        output += "-------------------\n";
        System.out.println(output);
    }
}
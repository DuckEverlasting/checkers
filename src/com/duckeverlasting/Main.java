package com.duckeverlasting;

import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.objects.Action;
import com.duckeverlasting.objects.ActionFinder;
import com.duckeverlasting.objects.GameBoard;

public class Main {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        ActionFinder actionFinder = gameBoard.getActionFinder();
        int[] remaining = gameBoard.getRemainingPieces();
        while (remaining[0] > 0 && remaining[1] > 0)
        {
            gameBoard.printBoard();
            Action nextAction = gameBoard.getActionInput();
            gameBoard.run(nextAction);
            if (
                nextAction.getType() != ActionType.JUMP ||
                actionFinder.getActions(nextAction.getDestination(), ActionType.JUMP).size() == 0
            )
            {
                gameBoard.endTurn();
            }
        }
        gameBoard.printBoard();
        int victor = remaining[0] == 0 ? 2 : 1;
        System.out.println("GAME OVER. PLAYER " + victor + " WINS.");
    }
}

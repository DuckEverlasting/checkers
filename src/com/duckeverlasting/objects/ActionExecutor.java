package com.duckeverlasting.objects;

import com.duckeverlasting.Utils;
import com.duckeverlasting.enums.ActionType;

public class ActionExecutor {
    private GameBoard gameBoard;

    public ActionExecutor(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    public void run(Action action)
    {
        int[] state = gameBoard.getState();
        int origin = action.getOrigin();
        GamePiece toMove = gameBoard.getGamePieces()[state[origin]];
        int destination = action.getDestination();
        state[origin] = -1;
        state[destination] = toMove.getId();
        toMove.setPosition(destination);
        
        if (action.getType() == ActionType.JUMP)
        {
            int target = Utils.getBetween(origin, destination);
            GamePiece toRemove = gameBoard.getGamePieces()[state[target]];
            state[target] = -1;
            toMove.setPosition(-1);
            int[] remainingPieces = gameBoard.getRemainingPieces();
            remainingPieces[toRemove.getPlayer()]--;
        }
    }
}
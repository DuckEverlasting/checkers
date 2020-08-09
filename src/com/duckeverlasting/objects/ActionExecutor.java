package com.duckeverlasting.objects;

import com.duckeverlasting.Helpers;
import com.duckeverlasting.enums.ActionType;

public class ActionExecutor {
    private Game game;

    public ActionExecutor(Game game)
    {
        this.game = game;
    }

    public void run(Action action)
    {
        int[] gameBoard = game.getgameBoard();
        int origin = action.getOrigin();
        GamePiece toMove = game.getGamePieces()[gameBoard[origin]];
        int destination = action.getDestination();
        gameBoard[origin] = -1;
        gameBoard[destination] = toMove.getId();
        toMove.setPosition(destination);
        
        if (action.getType() == ActionType.JUMP)
        {
            int target = Helpers.getBetween(origin, destination);
            GamePiece toRemove = game.getGamePieces()[gameBoard[target]];
            gameBoard[target] = -1;
            toMove.setPosition(-1);
            int[] remainingPieces = game.getRemainingPieces();
            remainingPieces[toRemove.getPlayer()]--;
        }
    }
}
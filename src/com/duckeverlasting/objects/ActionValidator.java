package com.duckeverlasting.objects;

import com.duckeverlasting.Helpers;
import com.duckeverlasting.enums.ActionType;

public class ActionValidator {
    Game game;


    public ActionValidator(Game game) {
        this.game = game;
    }

    public boolean isValidAction(ActionType type, int origin, int destination)
    {
        if (game.getgameBoard()[origin] == -1)
        {
            return false;
        }
        GamePiece gamePiece = game.getGamePieces()[game.getgameBoard()[origin]];
        if (type == ActionType.MOVE)
        {
            if (destination != -1 && this.game.getgameBoard()[destination] == -1)
            {
                return true;
            }
        } else if (type == ActionType.JUMP)
        {
            int target = Helpers.getBetween(origin, destination);
            if (target == -1 || game.getgameBoard()[target] == -1)
            {
                return false;
            }
            GamePiece targetGamePiece = game.getGamePieces()[game.getgameBoard()[target]];
            if (targetGamePiece.getPlayer() != gamePiece.player && destination != -1 && game.getgameBoard()[destination] != -1)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isValidAction(Action action)
    {
        return isValidAction(action.getType(), action.getOrigin(), action.getDestination());
    }
}
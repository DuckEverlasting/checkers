package com.duckeverlasting.objects;

import com.duckeverlasting.Helpers;
import com.duckeverlasting.enums.ActionType;

public class ActionValidator {
    public static boolean isValidAction(Action action, int[] gameBoard) {
        if (gameBoard[action.getOrigin()] == -1) {
            return false;
        }
        int gamePiecePlayer = Helpers.getPlayer(gameBoard[action.getOrigin()]);
        if (action.getType() == ActionType.MOVE) {
            if (action.getDestination() != -1 && gameBoard[action.getDestination()] == -1) {
                return true;
            }
        } else if (action.getType() == ActionType.JUMP) {
            int target = Helpers.getBetween(action.getOrigin(), action.getDestination());
            if (target == -1 || gameBoard[target] == -1) {
                return false;
            }
            int targetGamePiecePlayer = Helpers.getPlayer(gameBoard[target]);
            if (targetGamePiecePlayer != gamePiecePlayer
                    && action.getDestination() != -1
                    && gameBoard[action.getDestination()] == -1) {
                return true;
            }
        }
        return false;
    }
}
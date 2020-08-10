package com.duckeverlasting.objects;

import com.duckeverlasting.Helpers;
import com.duckeverlasting.enums.ActionType;

public class ActionExecutor {
    private final Game  game;

    public ActionExecutor(Game game) {
        this.game = game;
    }

    public void run(Action action) {
        int[] gameBoard = game.getgameBoard();
        game.setGameBoard(getChanges(action, gameBoard));
    }

    public static int[] getChanges(Action action, int[] gameBoard) {
        int origin = action.getOrigin();
        int gamePiece = gameBoard[origin];
        int destination = action.getDestination();
        gameBoard[origin] = -1;
        gameBoard[destination] = gamePiece;

        if (action.getType() == ActionType.JUMP) {
            int targetGamePiece = Helpers.getBetween(origin, destination);
            gameBoard[targetGamePiece] = -1;
        }
        if (!Helpers.isKing(gamePiece)) {
            if (
                (destination < 4 && Helpers.getPlayer(gamePiece) == 0)
                || (destination > 27 && Helpers.getPlayer(gamePiece) == 1)
            ) {
                gameBoard[destination] += 24;
            }
        }
        return gameBoard;
    }
}
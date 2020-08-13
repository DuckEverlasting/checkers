package com.duckeverlasting.objects;

import java.util.ArrayList;

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
        int[] resGameBoard = gameBoard.clone();
        resGameBoard[origin] = -1;
        resGameBoard[destination] = gamePiece;

        if (action.getType() == ActionType.JUMP) {
            int targetGamePiece = Helpers.getBetween(origin, destination);
            resGameBoard[targetGamePiece] = -1;
        }
        if (!Helpers.isKing(gamePiece)) {
            if (
                (destination < 4 && Helpers.getPlayer(gamePiece) == 0)
                || (destination > 27 && Helpers.getPlayer(gamePiece) == 1)
            ) {
                resGameBoard[destination] += 24;
            }
        }
        return resGameBoard;
    }

    public static int[] getChanges(ChainAction action, int[] gameBoard) {
        int origin = action.getOrigin();
        int gamePiece = gameBoard[origin];
        ArrayList<Integer> destinations = action.getDestinations();
        int[] resGameBoard = gameBoard.clone();
        int finalDest = destinations.get(destinations.size() - 1);
        resGameBoard[origin] = -1;
        resGameBoard[finalDest] = gamePiece;
        for (int i = 0; i < destinations.size(); i++) {
            int targetGamePiece = Helpers.getBetween(
                i == 0 ? origin : destinations.get(i - 1),
                destinations.get(i)
            );
            resGameBoard[targetGamePiece] = -1;
        }

        if (!Helpers.isKing(gamePiece)) {
            for (int dest : destinations) {
                if (
                    (dest < 4 && Helpers.getPlayer(gamePiece) == 0)
                    || (dest > 27 && Helpers.getPlayer(gamePiece) == 1)
                ) {
                    resGameBoard[finalDest] += 24;
                    break;
                }
            }
        }
        return resGameBoard;
    }
}
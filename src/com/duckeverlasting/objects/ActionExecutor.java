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
        int origin = action.getOrigin();
        GamePiece toMove = game.getGamePiece(gameBoard[origin]);
        int destination = action.getDestination();
        gameBoard[origin] = -1;
        gameBoard[destination] = toMove.getId();
        toMove.setPosition(destination);

        if (action.getType() == ActionType.JUMP) {
            int target = Helpers.getBetween(origin, destination);
            GamePiece toRemove = game.getGamePiece(gameBoard[target]);
            gameBoard[target] = -1;
            toRemove.setPosition(-1);
            int[] remainingPieces = game.getRemainingPieces();
            remainingPieces[toRemove.getPlayer()]--;
            game.setRemainingPieces(remainingPieces);
        }
        if (!toMove.isKing()) {
            if (
                (toMove.getPosition() < 4 && toMove.getPlayer() == 0)
                || (toMove.getPosition() > 27 && toMove.getPlayer() == 1)
            ) {
                toMove.setKing(true);
            }
        }
        game.setGameBoard(gameBoard);
    }

    public int[] project(Action action) {
        int[] gameBoard = game.getgameBoard();
        int origin = action.getOrigin();
        GamePiece toMove = game.getGamePiece(gameBoard[origin]);
        int destination = action.getDestination();
        gameBoard[origin] = -1;
        gameBoard[destination] = toMove.getId();
        toMove.setPosition(destination);

        if (action.getType() == ActionType.JUMP) {
            int target = Helpers.getBetween(origin, destination);
            GamePiece toRemove = game.getGamePiece(gameBoard[target]);
            gameBoard[target] = -1;
            toRemove.setPosition(-1);
            int[] remainingPieces = game.getRemainingPieces();
            remainingPieces[toRemove.getPlayer()]--;
            game.setRemainingPieces(remainingPieces);
        }
        if (!toMove.isKing()) {
            if (
                (toMove.getPosition() < 4 && toMove.getPlayer() == 0)
                || (toMove.getPosition() > 27 && toMove.getPlayer() == 1)
            ) {
                toMove.setKing(true);
            }
        }
        return gameBoard;
    }
}
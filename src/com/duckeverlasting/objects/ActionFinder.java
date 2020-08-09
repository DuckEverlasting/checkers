package com.duckeverlasting.objects;

import java.util.ArrayList;

import com.duckeverlasting.Helpers;
import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.enums.Direction;

public class ActionFinder {
    private final Game  game;

    public ActionFinder(Game game) {
        this.game = game;
    }

    public ArrayList<Action> getAllActions(int player, int[] gameBoard) {
        ArrayList<Action> actions = getAllActionsOfType(
            player, ActionType.JUMP, gameBoard
        );
        if (actions.size() > 0) {
            return actions;
        }
        actions.addAll(getAllActionsOfType(
            player, ActionType.MOVE, gameBoard
        ));
        return actions;
    }

    public ArrayList<Action> getAllActions(int player, int[] gameBoard, ActionType type) {
        return getAllActionsOfType(player, type, gameBoard);
    }
    
    public ArrayList<Action> getAllActions(int player, ActionType type) {
        return getAllActionsOfType(player, type, game.getgameBoard());
    }

    public ArrayList<Action> getAllActions(int player) {
        return getAllActions(player, game.getgameBoard());
    }

    private ArrayList<Action> getAllActionsOfType(int player, ActionType type, int[] gameBoard) {
        ArrayList<Action> actions = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if (gameBoard[i] == -1) {
                continue;
            }
            GamePiece gamePiece = game.getGamePiece(gameBoard[i]);
            if (gamePiece.getPlayer() == player) {
                actions.addAll(getActions(i, type, gameBoard));
            }
        }
        return actions;
    }

    public ArrayList<Action> getActions(int origin, ActionType type, int[] gameBoard) {
        ArrayList<Action> actions = new ArrayList<>();
        int id = gameBoard[origin];
        if (id == -1) {
            return actions;
        }
        GamePiece gamePiece = game.getGamePiece(id);
        if (gamePiece.getPlayer() == 0 || gamePiece.isKing()) {
            int upLeft = Helpers.getNeighbor(origin, Direction.UP_LEFT);
            int upRight = Helpers.getNeighbor(origin, Direction.UP_RIGHT);
            int twoUpLeft = Helpers.getNeighbor(upLeft, Direction.UP_LEFT);
            int twoUpRight = Helpers.getNeighbor(upRight, Direction.UP_RIGHT);
            Action actionUpRight = new Action(
                type,
                origin,
                type == ActionType.JUMP ? twoUpRight : upRight
            );
            if (game.getActionValidator().isValidAction(actionUpRight)) {
                actions.add(actionUpRight);
            }
            Action actionUpLeft = new Action(
                type,
                origin,
                type == ActionType.JUMP ? twoUpLeft : upLeft
            );
            if (game.getActionValidator().isValidAction(actionUpLeft)) {
                actions.add(actionUpLeft);
            }
        }
        if (gamePiece.getPlayer() == 1 || gamePiece.isKing()) {
            int downLeft = Helpers.getNeighbor(origin, Direction.DOWN_LEFT);
            int downRight = Helpers.getNeighbor(origin, Direction.DOWN_RIGHT);
            int twoDownLeft = Helpers.getNeighbor(downLeft, Direction.DOWN_LEFT);
            int twoDownRight = Helpers.getNeighbor(downRight, Direction.DOWN_RIGHT);
            Action actionDownRight = new Action(
                type,
                origin,
                type == ActionType.JUMP ? twoDownRight : downRight
            );
            if (game.getActionValidator().isValidAction(actionDownRight)) {
                actions.add(actionDownRight);
            }
            Action actionDownLeft = new Action(
                type,
                origin,
                type == ActionType.JUMP ? twoDownLeft : downLeft
            );
            if (game.getActionValidator().isValidAction(actionDownLeft)) {
                actions.add(actionDownLeft);
            }
        }
        return actions;
    }

    public ArrayList<Action> getActions(int origin, ActionType type) {
        return getActions(origin, type, game.getgameBoard());
    }

}

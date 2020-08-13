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
            int gamePiecePlayer = Helpers.getPlayer(gameBoard[i]);
            if (gamePiecePlayer == player) {
                actions.addAll(getActions(type, i, gameBoard));
            }
        }
        return actions;
    }

    private Action getActionInDir(ActionType type, int origin, Direction direction) {
        int oneStep = Helpers.getNeighbor(origin, direction);
        int twoSteps = Helpers.getNeighbor(oneStep, direction);
        return new Action(
            type,
            origin,
            type == ActionType.JUMP ? twoSteps : oneStep
        );
    }

    public ArrayList<Action> getActions(ActionType type, int origin, int[] gameBoard) {
        ArrayList<Action> actions = new ArrayList<>();
        int id = gameBoard[origin];
        if (id == -1) {
            return actions;
        }
        int gamePiecePlayer = Helpers.getPlayer(id);
        if (gamePiecePlayer == 0 || Helpers.isKing(id)) {
            Action actionUpRight = getActionInDir(type, origin, Direction.UP_RIGHT);
            if (game.getActionValidator().isValidAction(actionUpRight)) {
                actions.add(actionUpRight);
            }
            Action actionUpLeft = getActionInDir(type, origin, Direction.UP_LEFT);
            if (game.getActionValidator().isValidAction(actionUpLeft)) {
                actions.add(actionUpLeft);
            }
        }
        if (gamePiecePlayer == 1 || Helpers.isKing(id)) {
            Action actionDownRight = getActionInDir(type, origin, Direction.DOWN_RIGHT);
            if (game.getActionValidator().isValidAction(actionDownRight)) {
                actions.add(actionDownRight);
            }
            Action actionDownLeft = getActionInDir(type, origin, Direction.DOWN_LEFT);
            if (game.getActionValidator().isValidAction(actionDownLeft)) {
                actions.add(actionDownLeft);
            }
        }
        return actions;
    }

    public ArrayList<Action> getActions(ActionType type, int origin) {
        return getActions(type, origin, game.getgameBoard());
    }

}

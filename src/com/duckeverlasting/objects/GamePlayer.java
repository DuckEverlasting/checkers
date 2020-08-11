package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.Random;

import com.duckeverlasting.enums.ActionType;

public class GamePlayer {
    private Game    game;
    private int     difficulty;
    private Random  random;

    public GamePlayer(Game game, int difficulty) {
        this.game = game;
        this.difficulty = difficulty;
        random = new Random();
    }

    public Action getRandomAction(int[] board) {
        int player = game.getTurn() % 2;
        ArrayList<Action> actions = game.getActionFinder().getAllActions(player, board);
        if (actions.size() == 0) {
            return new Action(ActionType.NULL, -1, -1);
        }
        return actions.get(random.nextInt(actions.size()));
    }

    public Action getOptimalAction(int[] board) {
        int player = game.getTurn() % 2;
        boolean isCurrentPlayer = true;
        ArrayList<Action> actions = game.getActionFinder().getAllActions(player, board);
        for (Action action : actions) {
            int[] nextBoard = board.clone();
            nextBoard = ActionExecutor.getChanges(action, nextBoard);
            if (
                action.getType() != ActionType.JUMP
                || game.getActionFinder().getActions(
                    action.getDestination(), ActionType.JUMP
                ).size() != 0
            ) {
                isCurrentPlayer = !isCurrentPlayer;
            }
            ArrayList<Action> nextActions = game.getActionFinder().getAllActions(player, board);

        }
        return actions.get(random.nextInt(actions.size()));
    }

    private ArrayList<ChainAction> chainJumpAction(Action jumpAction) {
        ArrayList<ChainAction> actionChain = new ArrayList<>();
        return actionChain;
    }
}

package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.duckeverlasting.enums.ActionType;

public class GamePlayer {
    private Game                game;
    private int                 difficulty;
    private Random              random;
    private LinkedList<Action>  actionQueue;

    public GamePlayer(Game game, int difficulty) {
        this.game = game;
        this.difficulty = difficulty;
        random = new Random();
        actionQueue = new LinkedList<>();
    }

    public Action getRandomAction(int[] board) {
        int player = game.getTurn() % 2;
        ArrayList<Action> actions = game.getActionFinder().getAllActions(player, board);
        if (actions.size() == 0) {
            return new Action(ActionType.NULL, -1, -1);
        }
        return actions.get(random.nextInt(actions.size()));
    }

    public Action planAhead(int steps) {
        if (actionQueue.size() > 0) {
            return actionQueue.poll();
        }

        int player = game.getTurn() % 2;

        ArrayList<ArrayList<GameAction>> allPaths = new ArrayList<>();
        LinkedList<ArrayList<int[]>> queue = new LinkedList<>();
        // ArrayList<GameAction> bestActions = new ArrayList<GameAction>();
        // int bestValue = allActions.get(0).getValue();
        // for (GameAction action : allActions) {
        //     if (bestValue == action.getValue()) {
        //         bestActions.add(action);
        //     } else if (bestValue < action.getValue()) {
        //         bestActions.clear();
        //         bestActions.add(action);
        //     }
        // }
        // GameAction choice = bestActions.get(random.nextInt(bestActions.size()));
        // if (choice.getType() == ActionType.CHAIN_JUMP) {
        //     ChainAction chainChoice = (ChainAction)choice;
        //     queueChainAction(chainChoice);
        //     return new Action(ActionType.JUMP, chainChoice.getOrigin(), chainChoice.getDestinations().get(0));
        // } else {
        //     return (Action)choice; 
        // }
        return new Action(ActionType.NULL, -1, -1);
    }

    private void queueChainAction(ChainAction action) {
        for (int i = 1; i < action.getDestinations().size(); i++) {
            actionQueue.add(new Action(
                ActionType.JUMP,
                action.getDestinations().get(i - 1),
                action.getDestinations().get(i)
            ));
        }
    }
}

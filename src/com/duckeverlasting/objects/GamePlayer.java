package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.LinkedList;
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
        this.random = new Random();
        this.actionQueue = new LinkedList<>();
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
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            return actionQueue.poll();
        }

        int player = game.getTurn() % 2;

        PossibilityTree tree = new PossibilityTree(game.getgameBoard(), 7, player);

        System.out.println(tree.getAverageValuesOfMainBranches());
        
        GameAction optimal = tree.getOptimal();
        if (optimal.getType() == ActionType.CHAIN_JUMP) {
            ChainAction optimalChain = (ChainAction)optimal;
            queueChainAction(optimalChain);
            return new Action(ActionType.JUMP, optimal.getOrigin(), optimalChain.getDestinations().get(0));
        } else {
            return (Action)optimal;
        }

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

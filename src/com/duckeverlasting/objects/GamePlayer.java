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

    public Action getOptimalAction(int[] board) {
        if (actionQueue.size() > 0) {
            return actionQueue.poll();
        }
        int player = game.getTurn() % 2;
        // boolean isCurrentPlayer = true;
        ArrayList<Action> firstActions = game.getActionFinder().getAllActions(player, board);
        ArrayList<GameAction> allActions = new ArrayList<>();
        firstActions.forEach((action) -> {
            if (action.getType() == ActionType.JUMP) {
                allActions.addAll(getJumpChains(action));
            } else if (action.getType() == ActionType.MOVE) {
                allActions.add(action);
            }
        });
        ArrayList<GameAction> bestActions = new ArrayList<GameAction>();
        int bestValue = allActions.get(0).getValue();
        for (GameAction action : allActions) {
            if (bestValue == action.getValue()) {
                bestActions.add(action);
            } else if (bestValue < action.getValue()) {
                bestActions.clear();
                bestActions.add(action);
            }
        }
        // isCurrentPlayer = !isCurrentPlayer;
        GameAction choice = bestActions.get(random.nextInt(bestActions.size()));
        if (choice.getType() == ActionType.CHAIN_JUMP) {
            ChainAction chainChoice = (ChainAction)choice;
            Action next = new Action(
                ActionType.JUMP,
                chainChoice.getOrigin(),
                chainChoice.getDestinations().get(0)
            );
            for (int i = 1; i < chainChoice.getDestinations().size(); i++) {
                actionQueue.add(new Action(
                    ActionType.JUMP,
                    chainChoice.getDestinations().get(i - 1),
                    chainChoice.getDestinations().get(i)
                ));
            }
            return next;
        } else {
            return (Action)choice; 
        }
    }

    private ArrayList<ChainAction> getJumpChains(Action jumpAction) {
        ArrayList<ChainAction> chainActions = new ArrayList<>();
        Queue<ArrayList<Integer>> pathQueue = new LinkedList<>();
        Queue<int[]> boardQueue = new LinkedList<>();
        ArrayList<Integer> initialPath = new ArrayList<>();
        initialPath.add(jumpAction.getDestination());
        pathQueue.add(initialPath);
        boardQueue.add(ActionExecutor.getChanges(jumpAction, game.getgameBoard()));
        while (pathQueue.size() > 0) {
            ArrayList<Integer> currentPath = pathQueue.poll();
            int[] currentBoard = boardQueue.poll();
            ArrayList<Action> nextActions = game.getActionFinder().getActions(
                ActionType.JUMP,
                currentPath.get(currentPath.size() - 1),
                currentBoard
            );
            if (nextActions.size() > 0) {
                nextActions.forEach((Action action) -> {
                    ArrayList<Integer> newPath = new ArrayList<>(currentPath);
                    int[] newBoard = ActionExecutor.getChanges(action, currentBoard);
                    newPath.add(action.getDestination());
                    pathQueue.add(newPath);
                    boardQueue.add(newBoard);
                });
            } else {
                ChainAction chainAction = new ChainAction(
                    ActionType.CHAIN_JUMP,
                    jumpAction.getOrigin(),
                    currentPath
                );
                chainActions.add(chainAction);
            }
        }
        return chainActions;
    }
}

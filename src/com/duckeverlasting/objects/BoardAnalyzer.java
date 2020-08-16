package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.duckeverlasting.enums.ActionType;

/**
 * Here's the plan as it stands:
 * - build a tree structure based off the current board
 * - use matching arraylists to store child nodes and the actions that get to them
 * - keep track of how many branches from the root the current node is, and have a limit to how far the structure can go
 * - once the structure is built, traverse it
 * - keep track of values (alternating between negative and positive depending on whose move it is)
 * - select a path. how to do this? good question.
 * - need to figure out how to weight these things. can't just assume the opponent will always make the optimal choice.
 */


public class BoardAnalyzer {
    ActionFinder actionFinder = new ActionFinder();
    BoardManipulator manipulator = new BoardManipulator();

    public ArrayList<GameAction> calculatePossibleMoves(int player, int[] board) {
        ArrayList<Action> firstActions = actionFinder.getAllActions(player, board);
        ArrayList<GameAction> allActions = new ArrayList<>();
        firstActions.forEach((action) -> {
            if (action.getType() == ActionType.JUMP) {
                allActions.addAll(getJumpChains(action, board));
            } else if (action.getType() == ActionType.MOVE) {
                allActions.add(action);
            }
        });
        return allActions;
    }

    private ArrayList<ChainAction> getJumpChains(Action jumpAction, int[] board) {
        ArrayList<ChainAction> chainActions = new ArrayList<>();
        Queue<ArrayList<Integer>> pathQueue = new LinkedList<>();
        Queue<int[]> boardQueue = new LinkedList<>();
        ArrayList<Integer> initialPath = new ArrayList<>();
        initialPath.add(jumpAction.getDestination());
        pathQueue.add(initialPath);
        boardQueue.add(manipulator.getChanges(jumpAction, board));
        while (pathQueue.size() > 0) {
            ArrayList<Integer> currentPath = pathQueue.poll();
            int[] currentBoard = boardQueue.poll();
            ArrayList<Action> nextActions = actionFinder.getActions(
                ActionType.JUMP,
                currentPath.get(currentPath.size() - 1),
                currentBoard
            );
            if (nextActions.size() > 0) {
                nextActions.forEach((Action action) -> {
                    ArrayList<Integer> newPath = new ArrayList<>(currentPath);
                    int[] newBoard = manipulator.getChanges(action, currentBoard);
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

    public BoardManipulator getManipulator() {
        return manipulator;
    }
}
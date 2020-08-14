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


public class BoardAnalysis {
    private final Game                  game;
    private final int[]                 board;
    private final int                   stepsAhead;
    private final ArrayList<GameAction> possibleMoves;

    BoardAnalysis(Game game, int[] board, int stepsAhead) {
        this.game = game;
        this.board = board;
        this.stepsAhead = stepsAhead;
        this.possibleMoves = new ArrayList<GameAction>();
        int player = (game.getTurn() + stepsAhead) % 2;
        this.possibleMoves.addAll(calculatePossibleMoves(board, player));
    }

    public Game getGame() {
        return this.game;
    }

    public int[] getBoard() {
        return this.board.clone();
    }

    public int getStepsAhead() {
        return this.stepsAhead;
    }

    public ArrayList<GameAction> getPossibleMoves() {
        return new ArrayList<>(this.possibleMoves);
    }

    private ArrayList<GameAction> calculatePossibleMoves(int[] board, int player) {
        ArrayList<Action> firstActions = game.getActionFinder().getAllActions(player, board);
        ArrayList<GameAction> allActions = new ArrayList<>();
        firstActions.forEach((action) -> {
            if (action.getType() == ActionType.JUMP) {
                allActions.addAll(getJumpChains(action));
            } else if (action.getType() == ActionType.MOVE) {
                allActions.add(action);
            }
        });
        return allActions;
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
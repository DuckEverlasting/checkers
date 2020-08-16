package com.duckeverlasting.objects;

import java.util.ArrayList;

import com.duckeverlasting.Helpers;

public class TreeNode {
    private final TreeNode root;
    private final BoardAnalyzer analyzer;
    private final ArrayList<TreeNode> children = new ArrayList<>();
    private final ArrayList<GameAction> possibleActions = new ArrayList<>();;
    private final int value;
    private final int player;
    private final int depth;
    private final int maxDepth;

    // Root node constructor
    public TreeNode(int[] board, int maxDepth, int player) {
        this.root = this;
        this.analyzer = new BoardAnalyzer();
        this.player = player;
        this.value = 0;
        this.maxDepth = maxDepth;
        this.depth = 0;
        if (depth < maxDepth) {
            generateChildren(board);
        }
    }

    // Child node constructor
    public TreeNode(TreeNode root, int[] board, int maxDepth, int player, int value, int depth) {
        this.root = root;
        this.analyzer = root.getAnalyzer();
        this.player = player;
        this.value = value;
        this.maxDepth = maxDepth;
        this.depth = depth;
        if (depth < maxDepth) {
            generateChildren(board);
        }
    }

    private void generateChildren(int[] board) {
        this.possibleActions.addAll(analyzer.calculatePossibleMoves(player, board));
        for (GameAction action : possibleActions) {
            int[] newBoard = analyzer.getManipulator().getChanges(action, board);
            int value = action.getValue();
            if (Helpers.willBeKing(
                newBoard[action.getOrigin()],
                action.getFinalDestination())
            ) {
                value += 1;
            }
            if (depth % 2 == 1) {
                value *= -1;
            }
            children.add(new TreeNode(
                root,
                newBoard,
                maxDepth,
                player,
                value += value,
                depth + 1
            ));
        }
    }

    protected BoardAnalyzer getAnalyzer() {
        return this.analyzer;
    }

    public ArrayList<TreeNode> getChildren() {
        return new ArrayList<>(this.children);
    }

    public ArrayList<GameAction> getPossibleActions() {
        return new ArrayList<>(this.possibleActions);
    }

    public int getValue() {
        return this.value;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    @Override
    public String toString() {
        return "{" +
            "analyzer='" + getAnalyzer() + "'" +
            ", possibleActions='" + getPossibleActions() + "'" +
            ", value='" + getValue() + "'" +
            ", depth='" + getDepth() + "'" +
            "}";
    }

}
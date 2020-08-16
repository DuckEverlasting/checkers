package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Function;

import com.duckeverlasting.enums.ActionType;

public class PossibilityTree {
    TreeNode root;
    int maxDepth;
    int count = 0;

    PossibilityTree(int[] board, int maxDepth, int player) {
        this.root = new TreeNode(board, maxDepth, player);
        this.maxDepth = maxDepth;
    }

    public ArrayList<TreeNode> getLeaves() {
        return filterLeaves((TreeNode node) -> true);
    }

    public ArrayList<TreeNode> filterLeaves(Function<TreeNode, Boolean> filter) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> leaves = new ArrayList<>();
        queue.add(root);

        while (queue.size() > 0) {
            TreeNode currNode = queue.poll();
            if (currNode.getDepth() >= maxDepth && filter.apply(currNode)) {
                try {
                    leaves.add(currNode);
                } catch (Error e) {
                    System.out.println("MAX ARRAY DEPTH EXCEEDED. YOU HAVE BEEN CUT OFF.");
                    break;
                }
            }
            ArrayList<TreeNode> children = currNode.getChildren();
            for (int i = 0; i < children.size(); i++) {
                queue.add(children.get(i));
            }
        }
        return leaves;
    }

    public int getLeafCount() {
        filterLeaves((TreeNode node) -> {
            count++;
            return false;
        });
        return count;
    }

    public ArrayList<GameAction> getPossibleActions() {
        return this.root.getPossibleActions();
    }

    public ArrayList<Float> getAverageValuesOfMainBranches() {
        ArrayList<Float> averages = new ArrayList<>();
        for (TreeNode node : this.root.getChildren()) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            stack.add(node);
            int cumulative = 0;
            int divisor = 0;

            while (stack.size() > 0) {
                TreeNode currNode = stack.pop();
                if (currNode.getDepth() >= maxDepth) {
                    divisor++;
                    cumulative += currNode.getValue();
                }
                ArrayList<TreeNode> children = currNode.getChildren();
                for (int i = 0; i < children.size(); i++) {
                    stack.add(children.get(i));
                }
            }
            averages.add(((float)cumulative / (float)divisor));
        }
        return averages;
    }

    public GameAction getOptimal() {
        ArrayList<GameAction> possibleActions = getPossibleActions();
        if (possibleActions.size() == 0) {
            return new Action(ActionType.NULL, -1, -1);
        }
        ArrayList<Float> averages = getAverageValuesOfMainBranches();
        int best = 0;
        for (int i = 1; i < averages.size(); i++) {
            if (averages.get(i) > averages.get(best)) {
                best = i;
            }
        }
        return getPossibleActions().get(best);
    }
}
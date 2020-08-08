package com.duckeverlasting.objects;

import java.util.ArrayList;

import com.duckeverlasting.Utils;
import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.enums.Direction;

public class ActionFinder
{
    private GameBoard gameBoard;

    public ActionFinder(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }


    public ArrayList<Action> getAllActions(int player)
    {
        ArrayList<Action> actions = new ArrayList<>();
        int start = player == 0 ? 12 : 0;
        for (int i = start; i < start + 12; i++)
        {
            GamePiece gamePiece = this.gameBoard.getGamePieces()[i];
            actions.addAll(getActions(gamePiece));
        }
        for (Action action : actions)
        {
            System.out.println("FOUND: " + action);
        }
        return actions;
    }

    public ArrayList<Action> getActions(GamePiece gamePiece)
    {
        ArrayList<Action> actions = new ArrayList<>();
        if (gamePiece.getPlayer() == 0 || gamePiece.isKing())
        {
            int upLeft = Utils.getNeighbor(gamePiece.getPosition(), Direction.UP_LEFT);
            int upRight = Utils.getNeighbor(gamePiece.getPosition(), Direction.UP_RIGHT);
            int twoUpLeft = Utils.getNeighbor(upLeft, Direction.UP_LEFT);
            int twoUpRight = Utils.getNeighbor(upRight, Direction.UP_RIGHT);
            Action moveUpLeft = new Action(ActionType.MOVE, gamePiece.getPosition(), upLeft);
            if (isValidAction(moveUpLeft))
            {
                actions.add(moveUpLeft);
            }
            Action moveUpRight = new Action(ActionType.MOVE, gamePiece.getPosition(), upRight);
            if (isValidAction(moveUpRight))
            {
                actions.add(moveUpRight);
            }
            Action jumpUpLeft = new Action(ActionType.JUMP, gamePiece.getPosition(), twoUpLeft);
            if (isValidAction(jumpUpLeft))
            {
                actions.add(jumpUpLeft);
            }
            Action jumpUpRight = new Action(ActionType.JUMP, gamePiece.getPosition(), twoUpRight);
            if (isValidAction(jumpUpRight))
            {
                actions.add(jumpUpRight);
            }
        }
        if (gamePiece.getPlayer() == 1 || gamePiece.isKing())
        {
            int downLeft = Utils.getNeighbor(gamePiece.getPosition(), Direction.DOWN_LEFT);
            int downRight = Utils.getNeighbor(gamePiece.getPosition(), Direction.DOWN_RIGHT);
            int twoDownLeft = Utils.getNeighbor(downLeft, Direction.DOWN_LEFT);
            int twoDownRight = Utils.getNeighbor(downRight, Direction.DOWN_RIGHT);
            Action moveDownLeft = new Action(ActionType.MOVE, gamePiece.getPosition(), downLeft);
            if (isValidAction(moveDownLeft))
            {
                actions.add(moveDownLeft);
            }
            Action moveDownRight = new Action(ActionType.MOVE, gamePiece.getPosition(), downRight);
            if (isValidAction(moveDownRight))
            {
                actions.add(moveDownRight);
            }
            Action jumpDownLeft = new Action(ActionType.JUMP, gamePiece.getPosition(), twoDownLeft);
            if (isValidAction(jumpDownLeft))
            {
                actions.add(jumpDownLeft);
            }
            Action jumpDownRight = new Action(ActionType.JUMP, gamePiece.getPosition(), twoDownRight);
            if (isValidAction(jumpDownRight))
            {
                actions.add(jumpDownRight);
            }
        }
        return actions;
    }

    public ArrayList<Action> getActions(int origin, ActionType type)
    {
        ArrayList<Action> actions = new ArrayList<>();
        int id = gameBoard.getState()[origin];
        if (id == -1)
        {
            return actions;
        }
        GamePiece gamePiece = gameBoard.getGamePieces()[id];
        if (gamePiece.getPlayer() == 0 || gamePiece.isKing())
        {
            int upLeft = Utils.getNeighbor(gamePiece.getPosition(), Direction.UP_LEFT);
            int upRight = Utils.getNeighbor(gamePiece.getPosition(), Direction.UP_RIGHT);
            int twoUpLeft = Utils.getNeighbor(upLeft, Direction.UP_LEFT);
            int twoUpRight = Utils.getNeighbor(upRight, Direction.UP_RIGHT);
            Action actUpRight = new Action(type, origin, type == ActionType.JUMP ? twoUpRight : upRight);
            if (isValidAction(actUpRight))
            {
                actions.add(actUpRight);
            }
            Action actUpLeft = new Action(type, origin, type == ActionType.JUMP ? twoUpLeft : upLeft);
            if (isValidAction(actUpLeft))
            {
                actions.add(actUpLeft);
            }
        }
        if (gamePiece.getPlayer() == 1 || gamePiece.isKing())
        {
            int downLeft = Utils.getNeighbor(gamePiece.getPosition(), Direction.DOWN_LEFT);
            int downRight = Utils.getNeighbor(gamePiece.getPosition(), Direction.DOWN_RIGHT);
            int twoDownLeft = Utils.getNeighbor(downLeft, Direction.DOWN_LEFT);
            int twoDownRight = Utils.getNeighbor(downRight, Direction.DOWN_RIGHT);
            Action actDownRight = new Action(type, origin, type == ActionType.JUMP ? twoDownRight : downRight);
            if (isValidAction(actDownRight))
            {
                actions.add(actDownRight);
            }
            Action actDownLeft = new Action(type, origin, type == ActionType.JUMP ? twoDownLeft : downLeft);
            if (isValidAction(actDownLeft))
            {
                actions.add(actDownLeft);
            }
        }
        return actions;
    }

    private boolean isValidAction(ActionType type, int origin, int destination)
    {
        if (gameBoard.getState()[origin] == -1)
        {
            return false;
        }
        GamePiece gamePiece = gameBoard.getGamePieces()[gameBoard.getState()[origin]];
        if (type == ActionType.MOVE)
        {
            if (destination != -1 && this.gameBoard.getState()[destination] == -1)
            {
                return true;
            }
        } else if (type == ActionType.JUMP)
        {
            int target = Utils.getBetween(origin, destination);
            if (target == -1 || gameBoard.getState()[target] == -1)
            {
                return false;
            }
            GamePiece targetGamePiece = gameBoard.getGamePieces()[gameBoard.getState()[target]];
            if (targetGamePiece.getPlayer() != gamePiece.player && destination != -1 && gameBoard.getState()[destination] != -1)
            {
                return true;
            }
        }
        return false;
    }

    private boolean isValidAction(Action action)
    {
        return isValidAction(action.getType(), action.getOrigin(), action.getDestination());
    }
}

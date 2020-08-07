package com.duckeverlasting.objects;

import java.util.ArrayList;

import com.duckeverlasting.Utils;
import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.enums.Direction;

public class ActionGenerator
{
    private GameBoard gameBoard;

    public ActionGenerator(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }


    public ArrayList<Action> getAllActions(int player)
    {
        ArrayList<Action> actions = new ArrayList<>();
        int start = player == 0 ? 0 : 12;
        for (int i = start; i < start + 12; i++)
        {
            GamePiece gamePiece = this.gameBoard.getGamePieces()[i];
            actions.addAll(getActions(gamePiece));
        }
        return actions;
    }

    public ArrayList<Action> getActions(GamePiece gamePiece)
    {
        ArrayList<Action> actions = new ArrayList<>();
        if (gamePiece.getPlayer() == 0 || gamePiece.getIsKing())
        {
            addIfValidAction(actions, gamePiece, ActionType.MOVE, Direction.UP_LEFT);
            addIfValidAction(actions, gamePiece, ActionType.MOVE, Direction.UP_RIGHT);
            addIfValidAction(actions, gamePiece, ActionType.JUMP, Direction.UP_LEFT);
            addIfValidAction(actions, gamePiece, ActionType.JUMP, Direction.UP_RIGHT);
        }
        if (gamePiece.getPlayer() == 1 || gamePiece.getIsKing())
        {
            addIfValidAction(actions, gamePiece, ActionType.MOVE, Direction.DOWN_LEFT);
            addIfValidAction(actions, gamePiece, ActionType.MOVE, Direction.DOWN_RIGHT);
            addIfValidAction(actions, gamePiece, ActionType.JUMP, Direction.DOWN_LEFT);
            addIfValidAction(actions, gamePiece, ActionType.JUMP, Direction.DOWN_RIGHT);
        }
        return actions;
    }

    public ArrayList<Action> getActions(GamePiece gamePiece, ActionType type)
    {
        ArrayList<Action> actions = new ArrayList<>();
        if (gamePiece.getPlayer() == 0 || gamePiece.getIsKing())
        {
            addIfValidAction(actions, gamePiece, type, Direction.UP_LEFT);
            addIfValidAction(actions, gamePiece, type, Direction.UP_RIGHT);
        }
        if (gamePiece.getPlayer() == 1 || gamePiece.getIsKing())
        {
            addIfValidAction(actions, gamePiece, type, Direction.DOWN_LEFT);
            addIfValidAction(actions, gamePiece, type, Direction.DOWN_RIGHT);
        }
        return actions;
    }

    private void addIfValidAction(ArrayList<Action> actions, GamePiece gamePiece, ActionType action, Direction direction)
    {
        int origin = gamePiece.getPosition();
        if (gameBoard.getState()[origin] == -1)
        {
            return;
        } else if (action == ActionType.MOVE)
        {
            int destination = Utils.getNeighbor(origin, direction);
            if (destination != -1 && this.gameBoard.getState()[destination] == -1)
            {
                actions.add(new Action(gamePiece, destination));
            }
        } else if (action == ActionType.JUMP)
        {
            int target = Utils.getNeighbor(origin, direction);
            if (target == -1 || gameBoard.getState()[target] == -1) {
                return;
            }
            GamePiece targetGamePiece = gameBoard.getGamePieces()[gameBoard.getState()[target]];
            int destination = Utils.getNeighbor(target, direction);
            if (destination != -1 && gameBoard.getState()[destination] != -1)
            {
                actions.add(new Action(gamePiece, destination, targetGamePiece));
            }
        } else {
            throw new Error("Action " + action + " not recognized."); 
        }
    }
}

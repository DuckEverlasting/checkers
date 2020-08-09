package com.duckeverlasting.objects;

import java.util.ArrayList;

import com.duckeverlasting.Helpers;
import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.enums.Direction;

public class ActionFinder
{
    private Game game;

    public ActionFinder(Game game)
    {
        this.game = game;
    }


    public ArrayList<Action> getAllActions(int player)
    {
        ArrayList<Action> actions = new ArrayList<>();
        int start = player == 0 ? 12 : 0;
        for (int i = start; i < start + 12; i++)
        {
            GamePiece gamePiece = game.getGamePieces()[i];
            actions.addAll(getActions(gamePiece.getPosition(), ActionType.JUMP));
        }
        if (actions.size() > 0) {
            return actions;
        }
        for (int i = start; i < start + 12; i++)
        {
            GamePiece gamePiece = game.getGamePieces()[i];
            actions.addAll(getActions(gamePiece.getPosition(), ActionType.MOVE));
        }
        return actions;
    }

    public ArrayList<Action> getActions(int origin, ActionType type)
    {
        ArrayList<Action> actions = new ArrayList<>();
        int id = game.getgameBoard()[origin];
        if (id == -1)
        {
            return actions;
        }
        GamePiece gamePiece = game.getGamePieces()[id];
        if (gamePiece.getPlayer() == 0 || gamePiece.isKing())
        {
            int upLeft = Helpers.getNeighbor(origin, Direction.UP_LEFT);
            int upRight = Helpers.getNeighbor(origin, Direction.UP_RIGHT);
            int twoUpLeft = Helpers.getNeighbor(upLeft, Direction.UP_LEFT);
            int twoUpRight = Helpers.getNeighbor(upRight, Direction.UP_RIGHT);
            Action actUpRight = new Action(type, origin, type == ActionType.JUMP ? twoUpRight : upRight);
            if (game.isValidAction(actUpRight))
            {
                actions.add(actUpRight);
            }
            Action actUpLeft = new Action(type, origin, type == ActionType.JUMP ? twoUpLeft : upLeft);
            if (game.isValidAction(actUpLeft))
            {
                actions.add(actUpLeft);
            }
        }
        if (gamePiece.getPlayer() == 1 || gamePiece.isKing())
        {
            int downLeft = Helpers.getNeighbor(origin, Direction.DOWN_LEFT);
            int downRight = Helpers.getNeighbor(origin, Direction.DOWN_RIGHT);
            int twoDownLeft = Helpers.getNeighbor(downLeft, Direction.DOWN_LEFT);
            int twoDownRight = Helpers.getNeighbor(downRight, Direction.DOWN_RIGHT);
            Action actDownRight = new Action(type, origin, type == ActionType.JUMP ? twoDownRight : downRight);
            if (game.isValidAction(actDownRight))
            {
                actions.add(actDownRight);
            }
            Action actDownLeft = new Action(type, origin, type == ActionType.JUMP ? twoDownLeft : downLeft);
            if (game.isValidAction(actDownLeft))
            {
                actions.add(actDownLeft);
            }
        }
        return actions;
    }
}

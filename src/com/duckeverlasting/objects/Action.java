package com.duckeverlasting.objects;

import com.duckeverlasting.enums.ActionType;

public class Action {
    private ActionType type;
    private GamePiece toMove;
    private GamePiece toRemove;
    private int destination;

    Action(GamePiece toMove, int destination)
    {
        this.type = ActionType.MOVE;
        this.toMove = toMove;
        this.destination = destination;
    }
    
    Action(GamePiece toMove, int destination, GamePiece toRemove)
    {
        this.type = ActionType.JUMP;
        this.toMove = toMove;
        this.destination = destination;
        this.toRemove = toRemove;
    }
}
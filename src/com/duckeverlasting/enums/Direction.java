package com.duckeverlasting.enums;

public enum Direction
{
    UP_LEFT(ActionType.MOVE),
    UP_RIGHT(ActionType.MOVE),
    DOWN_RIGHT(ActionType.MOVE),
    DOWN_LEFT(ActionType.MOVE),
    TWO_UP_LEFT(ActionType.JUMP),
    TWO_UP_RIGHTTWO_UP_LEFT(ActionType.JUMP),
    TWO_DOWN_RIGHTTWO_UP_LEFT(ActionType.JUMP),
    TWO_DOWN_LEFTTWO_UP_LEFT(ActionType.JUMP);

    public final ActionType type;

    Direction(ActionType type)
    {
        this.type = type;
    }
}

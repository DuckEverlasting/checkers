package com.duckeverlasting.objects;

import com.duckeverlasting.enums.ActionType;

public class Action implements GameAction{
    private ActionType  type;
    private int         origin;
    private int         destination;
    private int         value;

    public Action(ActionType type, int origin, int destination) {
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        switch(type) {
            case MOVE:
                value = 0;
                break;
            case JUMP:
                value = 1;
                break;
            default:
                value = 0;
                break;
        }
    }

    @Override
    public ActionType getType() {
        return this.type;
    }

    @Override
    public int getOrigin() {
        return this.origin;
    }

    public int getDestination() {
        return this.destination;
    }
    
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public boolean isEqualTo(GameAction action) {
        if (action instanceof Action == false) {
            return false;
        }
        return action.getType() == type && action.getOrigin() == origin && ((Action) action).getDestination() == destination;
    }

    @Override
    public String toString() {
        return "{" + " type='" + getType() + "'" + ", origin='" + getOrigin() + "'" + ", destination='"
                + getDestination() + "'" + "}";
    }
}
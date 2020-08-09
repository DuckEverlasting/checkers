package com.duckeverlasting.objects;

import com.duckeverlasting.enums.ActionType;

public class Action {
    private ActionType  type;
    private int         origin;
    private int         destination;

    public Action(ActionType type, int origin, int destination) {
        this.type = type;
        this.origin = origin;
        this.destination = destination;
    }

    public ActionType getType() {
        return this.type;
    }

    public int getOrigin() {
        return this.origin;
    }

    public int getDestination() {
        return this.destination;
    }

    public boolean isEqualTo(Action action) {
        return action.getType() == type && action.getOrigin() == origin && action.getDestination() == destination;
    }

    @Override
    public String toString() {
        return "{" + " type='" + getType() + "'" + ", origin='" + getOrigin() + "'" + ", destination='"
                + getDestination() + "'" + "}";
    }

}
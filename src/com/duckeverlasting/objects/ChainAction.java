package com.duckeverlasting.objects;

import java.util.Arrays;

import com.duckeverlasting.enums.ActionType;

public class ChainAction implements GameAction{
    private ActionType  type;
    private int         origin;
    private int[]       destinations;
    private int         value;

    public ChainAction(ActionType type, int origin, int[] destinations) {
        this.type = type;
        this.origin = origin;
        this.destinations = destinations;
        value = destinations.length;
    }

    @Override
    public ActionType getType() {
        return this.type;
    }

    @Override
    public int getOrigin() {
        return this.origin;
    }

    public int[] getDestinations() {
        return this.destinations;
    }
    
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public boolean isEqualTo(GameAction action) {
        if (action instanceof ChainAction == false) {
            return false;
        }
        return action.getType() == type
            && action.getOrigin() == origin
            && Arrays.equals(
                ((ChainAction) action).getDestinations(),
                destinations
            );
    }

    @Override
    public String toString() {
        return "{" + " type='" + getType() + "'" + ", origin='" + getOrigin() + "'" + ", destination='"
                + getDestinations() + "'" + "}";
    }
}
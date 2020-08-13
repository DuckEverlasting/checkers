package com.duckeverlasting.objects;

import java.util.ArrayList;

import com.duckeverlasting.enums.ActionType;

public class ChainAction implements GameAction{
    private ActionType          type;
    private int                 origin;
    private ArrayList<Integer>  destinations;
    private int                 value;

    public ChainAction(ActionType type, int origin, ArrayList<Integer> destinations) {
        this.type = type;
        this.origin = origin;
        this.destinations = destinations;
        value = destinations.size();
    }

    @Override
    public ActionType getType() {
        return this.type;
    }

    @Override
    public int getOrigin() {
        return this.origin;
    }

    public ArrayList<Integer> getDestinations() {
        return new ArrayList<>(this.destinations);
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
            && destinations.equals(((ChainAction) action).getDestinations());
    }

    @Override
    public String toString() {
        return "{" + " type='" + getType() + "'" + ", origin='" + getOrigin() + "'" + ", destination='"
                + getDestinations() + "'" + "}";
    }
}
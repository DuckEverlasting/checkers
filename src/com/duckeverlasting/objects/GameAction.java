package com.duckeverlasting.objects;

import com.duckeverlasting.enums.ActionType;

public interface GameAction {
    public ActionType   getType();
    public int          getOrigin();
    public int          getValue();
    public boolean      isEqualTo(GameAction action);
}
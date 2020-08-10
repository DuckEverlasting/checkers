package com.duckeverlasting.enums;

public enum ActionType {
    MOVE(0), JUMP(1), NULL(-1);

    public final int value;

    ActionType(int value) {
        this.value = value;
    }
}
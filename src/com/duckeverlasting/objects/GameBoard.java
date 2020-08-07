package com.duckeverlasting.objects;

import java.util.Arrays;

public class GameBoard
{
    private int[] state;
    private int turnCounter;
    private GamePiece[] gamePieces;
    private int[] score;

    public GameBoard() {
        this.state = new int[32];
        this.turnCounter = 0;
        this.score = new int[]{0, 0};
        this.gamePieces = new GamePiece[24];
        Arrays.fill(state, 12, 19, 0);
        for (int i = 1; i <= 12; i++) {
            gamePieces[i] = new GamePiece(i, 0, i);
        }
        for (int i = 13; i <= 24; i++) {
            gamePieces[i] = new GamePiece(i, 1, i + 6);
        }
    }
}

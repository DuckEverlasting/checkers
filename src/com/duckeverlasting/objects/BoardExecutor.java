package com.duckeverlasting.objects;

public class BoardExecutor extends BoardManipulator {
    private final Game  game;

    public BoardExecutor(Game game) {
        this.game = game;
    }

    public void run(Action action) {
        int[] gameBoard = game.getgameBoard();
        game.setGameBoard(getChanges(action, gameBoard));
    }
}
package com.duckeverlasting.objects;

import java.util.Arrays;

public class GameBoard
{
    private int[] state;
    private int turnCounter;
    private GamePiece[] gamePieces;
    private int[] score;
    private MoveGenerator moveGenerator;

    private int activePiece = -1;

    public GameBoard()
    {
        state = new int[32];
        turnCounter = 0;
        score = new int[]{0, 0};
        gamePieces = new GamePiece[24];
        moveGenerator = new MoveGenerator(this);
        Arrays.fill(state, 12, 19, 0);
        for (int i = 1; i <= 12; i++) {
            gamePieces[i] = new GamePiece(i, 0, i);
        }
        for (int i = 13; i <= 24; i++) {
            gamePieces[i] = new GamePiece(i, 1, i + 6);
        }
    }

    private void checkIfGameOver()
    {

    }

    public void takeTurn()
    {

    }

    /* what do we have so far?
        - we have a gameboard and pieces. The gameboard constructor sets up all the state we need, I believe.
        - we have the ability to get the destinations of moves (not jumps yet)
        - we have structure in place (but no logic yet) for where we'll call turns and where we'll check for
            available moves
        - we have an interface for actions, and two types of action set aside.

        - we need game logic. specifically we don't have a way to interact with the player(s) yet.
        - if we're building AI, we have none of that.

        - so how will a game go?
        - one player will go first. they will take a turn. if a win condition is not triggered, then the other player
            will take a turn. And so on and so forth.
        - a turn will consist of:
            - checking for available actions (useful when checking if a player is forced into a jump)
            - collecting input from player
            - checking if action is valid
            - if it is, making the action. (either moving or jumping)
            - if it is a jump action, check to see if a further jump is available. if it is, continue the turn.
            - for every action, if the gamepiece lands in the opposing end of the board (and is not yet a king)
                it becomes a king.
            - when all actions are done, end the turn. (check for win condition here)
        - that's pretty much it, actually.
        - maybe make a way to print out the board on the console :D
        - String data = console.readLine("<PROMPT TEXT>");
    */
}
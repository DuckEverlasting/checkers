package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.Arrays;

import com.duckeverlasting.Utils;

public class GameBoard
{
    private final int[] state;
    private final GamePiece[] gamePieces;
    private int turn;
    private final int[] remainingPieces;
    private ActionFinder actionFinder;
    private ActionExecutor actionExecutor;
    private Printer printer;

    public GameBoard()
    {
        state = new int[32];
        turn = 0;
        remainingPieces = new int[]{12, 12};
        gamePieces = new GamePiece[24];
        actionFinder = new ActionFinder(this);
        actionExecutor = new ActionExecutor(this);
        printer = new Printer(this);
        Arrays.fill(state, 12, 20, -1);
        for (int i = 0; i < 12; i++) {
            gamePieces[i] = new GamePiece(i, 1, i);
            state[i] = i;
        }
        for (int i = 12; i < 24; i++) {
            gamePieces[i] = new GamePiece(i, 0, i + 6);
            state[i + 8] = i;
        }
    }

    public int[] getState()
    {
        return state;
    }

    public GamePiece[] getGamePieces()
    {
        return gamePieces;
    }

    public int getTurn()
    {
        return turn;
    }

    public int[] getRemainingPieces()
    {
        return remainingPieces;
    }

    public ActionFinder getActionFinder()
    {
        return actionFinder;
    }

    public void run(Action action)
    {
        actionExecutor.run(action);
    }

    public void printBoard()
    {
        printer.print();
    }

    public void endTurn()
    {
        turn++;
    }

    public Action getActionInput()
    {
        int currentPlayer = turn % 2;
        ArrayList<Action> actions = actionFinder.getAllActions(currentPlayer);
        System.out.println(actions.size() + " MOVES AVAILABLE.");
        String input = System.console().readLine("YOUR MOVE, PLAYER " + (currentPlayer + 1) + ": ");
        Action parsedAction = Utils.parseInput(input);
        boolean test = false;
        System.out.println("RECIEVED: " + parsedAction.toString());
        for (Action action : actions)
        {
            System.out.println("MATCHING TO: " + action.toString());
        
            if (action.isEqualTo(parsedAction))
            {
                test = true;
            }
        }
        if (test == true)
        {
            return parsedAction;
        } else
        {
            System.out.println("INVALID MOVE.");
            return getActionInput();
        }
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
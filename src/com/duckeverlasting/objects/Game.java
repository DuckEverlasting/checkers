package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private final int               numOfPlayers;
    private final GamePiece[]       gamePieces;
    private final ActionFinder      actionFinder;
    private final ActionExecutor    actionExecutor;
    private final ActionValidator   actionValidator;
    private final GamePlayer        gamePlayer;
    private final InputParser       inputParser;
    private final Printer           printer;
    private int[]                   gameBoard;
    private int[]                   remainingPieces;
    private int                     turn;

    public Game(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        gameBoard = new int[32];
        turn = 0;
        remainingPieces = new int[] { 12, 12 };
        gamePieces = new GamePiece[24];
        actionFinder = new ActionFinder(this);
        actionExecutor = new ActionExecutor(this);
        actionValidator = new ActionValidator(this);
        gamePlayer = new GamePlayer(this);
        inputParser = new InputParser(this);
        printer = new Printer(this);
        // set up gameBoard
        for (int i = 0; i < 12; i++) {
            gamePieces[i] = new GamePiece(i, 1, i);
            gameBoard[i] = i;
        }
        Arrays.fill(gameBoard, 12, 20, -1);
        for (int i = 12; i < 24; i++) {
            gamePieces[i] = new GamePiece(i, 0, i + 8);
            gameBoard[i + 8] = i;
        }
    }

    public int[] getgameBoard() {
        return gameBoard.clone();
    }

    public void setGameBoard(int[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GamePiece getGamePiece(int id) {
        return gamePieces[id];
    }

    public int getTurn() {
        return turn;
    }

    public void endTurn() {
        turn++;
    }

    public int[] getRemainingPieces() {
        return remainingPieces.clone();
    }

    public void setRemainingPieces(int[] remainingPieces) {
        this.remainingPieces = remainingPieces;
    }

    public ActionFinder getActionFinder() {
        return actionFinder;
    }

    public ActionValidator getActionValidator() {
        return actionValidator;
    }

    public void run(Action action) {
        actionExecutor.run(action);
    }

    public void printBoard() {
        printer.print();
    }

    public void printBoard(ArrayList<Integer> highlights) {
        printer.print(highlights);
    }

    public Action getNextAction() {
        int currentPlayer = turn % 2;
        if (currentPlayer == 1 && numOfPlayers == 1) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            return gamePlayer.getRandomAction(getgameBoard());
        }
        ArrayList<Action> actions = actionFinder.getAllActions(currentPlayer);
        System.out.println(actions.size() + " MOVES AVAILABLE.");
        String input = System.console().readLine("YOUR MOVE, " + (currentPlayer == 0 ? "RED" : "WHITE") + ": ");
        Action parsedAction = inputParser.parseInput(input);
        if (parsedAction == null) {
            ArrayList<Integer> highlights = new ArrayList<>();
            for (Action action : actions) {
                highlights.add(action.getOrigin());
            }
            printBoard(highlights);
            System.out.println("INVALID MOVE.");
            return getNextAction();
        }
        boolean test = false;
        for (Action action : actions) {
            if (action.isEqualTo(parsedAction)) {
                test = true;
                break;
            }
        }
        if (test == true) {
            return parsedAction;
        } else {
            ArrayList<Integer> highlights = new ArrayList<>();
            for (Action action : actions) {
                highlights.add(action.getOrigin());
            }
            printBoard(highlights);
            System.out.println("INVALID MOVE.");
            return getNextAction();
        }
    }
}
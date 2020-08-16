package com.duckeverlasting;

import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.objects.Action;
import com.duckeverlasting.objects.ActionFinder;
import com.duckeverlasting.objects.Game;

public class Main {

    public static void main(String[] args) {
        int numOfPlayers = 0;
        int difficulty = 0;
        int winState = 0;
        while (numOfPlayers != 1 && numOfPlayers != 2) {
            numOfPlayers = Integer.parseInt(System.console().readLine(
                "\n\n\n\n\n\n\n\n\n\nNUMBER OF PLAYERS? "
            ));
        }
        while (difficulty <= 0 || difficulty > 3) {
            difficulty = Integer.parseInt(System.console().readLine(
                "\n\n(1 = easy    2 = normal    3 = hard)\nDIFFICULTY? "
            ));
        }
        Game game = new Game(numOfPlayers, difficulty);
        ActionFinder actionFinder = game.getActionFinder();
        while (winState == 0) {
            game.printBoard();
            Action nextAction = game.getNextAction();
            if (nextAction.getType() == ActionType.NULL) {
                int currentPlayer = game.getTurn() % 2;
                winState = currentPlayer == 1 ? 1 : 2;
                break;
            }
            game.run(nextAction);
            if (
                nextAction.getType() != ActionType.JUMP
                || actionFinder.getActions(
                    ActionType.JUMP,
                    nextAction.getDestination(),
                    game.getgameBoard()
                ).size() == 0
            ) {
                game.endTurn();
            }
        }
        game.printBoard();
        String victor = winState == 1 ? "RED" : "WHITE";
        System.out.println("GAME OVER. " + victor + " WINS.");
    }
}

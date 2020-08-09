package com.duckeverlasting;

import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.objects.Action;
import com.duckeverlasting.objects.ActionFinder;
import com.duckeverlasting.objects.Game;

public class Main {

    public static void main(String[] args) {
        int numOfPlayers = 0;
        while (numOfPlayers != 1 && numOfPlayers != 2) {
            numOfPlayers = Integer.parseInt(System.console().readLine("\n\n\n\n\n\n\n\n\n\nNUMBER OF PLAYERS? "));
        }
        Game game = new Game(numOfPlayers);
        ActionFinder actionFinder = game.getActionFinder();
        while (game.getRemainingPieces()[0] > 0 && game.getRemainingPieces()[1] > 0) {
            game.printBoard();
            Action nextAction = game.getNextAction();
            game.run(nextAction);
            if (
                nextAction.getType() != ActionType.JUMP
                || actionFinder.getActions(nextAction.getDestination(), ActionType.JUMP).size() == 0
            ) {
                game.endTurn();
            }
        }
        game.printBoard();
        String victor = game.getRemainingPieces()[0] == 0 ? "WHITE" : "RED";
        System.out.println("GAME OVER. " + victor + " WINS.");
    }
}

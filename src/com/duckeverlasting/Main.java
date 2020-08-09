package com.duckeverlasting;

import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.objects.Action;
import com.duckeverlasting.objects.ActionFinder;
import com.duckeverlasting.objects.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        ActionFinder actionFinder = game.getActionFinder();
        int[] remaining = game.getRemainingPieces();
        while (remaining[0] > 0 && remaining[1] > 0)
        {
            game.printBoard();
            Action nextAction = game.getActionInput();
            game.run(nextAction);
            if (
                nextAction.getType() != ActionType.JUMP ||
                actionFinder.getActions(nextAction.getDestination(), ActionType.JUMP).size() == 0
            )
            {
                game.endTurn();
            }
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        game.printBoard();
        int victor = remaining[0] == 0 ? 2 : 1;
        System.out.println("GAME OVER. PLAYER " + victor + " WINS.");
    }
}

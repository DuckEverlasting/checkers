package com.duckeverlasting.objects;

import java.util.ArrayList;
import java.util.Random;

public class GamePlayer {
    private Game                game;
    private Random              random;

    public GamePlayer(Game game) {
        this.game = game;
        random = new Random();
    }

    public Action getRandomAction(int[] board) {
        int player = game.getTurn() % 2;
        ArrayList<Action> actions = game.getActionFinder().getAllActions(player, board);
        return actions.get(random.nextInt(actions.size()));
    }
}
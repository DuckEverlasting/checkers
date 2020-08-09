package com.duckeverlasting.objects;

import java.util.AbstractMap;
import java.util.Map;

import com.duckeverlasting.Helpers;
import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.enums.Direction;

public class InputParser {
    private final Game  game;

    private static Map<String, Direction> directionLookup = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, Direction>("ul", Direction.UP_LEFT),
            new AbstractMap.SimpleEntry<String, Direction>("upleft", Direction.UP_LEFT),
            new AbstractMap.SimpleEntry<String, Direction>("up left", Direction.UP_LEFT),
            new AbstractMap.SimpleEntry<String, Direction>("up-left", Direction.UP_LEFT),
            new AbstractMap.SimpleEntry<String, Direction>("ur", Direction.UP_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("upright", Direction.UP_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("up right", Direction.UP_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("up-right", Direction.UP_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("dr", Direction.DOWN_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("downright", Direction.DOWN_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("down right", Direction.DOWN_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("down-right", Direction.DOWN_RIGHT),
            new AbstractMap.SimpleEntry<String, Direction>("dl", Direction.DOWN_LEFT),
            new AbstractMap.SimpleEntry<String, Direction>("downleft", Direction.DOWN_LEFT),
            new AbstractMap.SimpleEntry<String, Direction>("down left", Direction.DOWN_LEFT),
            new AbstractMap.SimpleEntry<String, Direction>("down-left", Direction.DOWN_LEFT));

    public InputParser(Game game) {
        this.game = game;
    }

    public Action parseInput(String input) {
        String[] array = input.split(" ");
        int origin;
        ActionType type;
        int destination;
        try {
            int id = Integer.parseInt(array[0]);
            origin = game.getGamePiece(id).getPosition();
            Direction direction = directionLookup.get(array[1]);
            int neighbor = Helpers.getNeighbor(origin, direction);
            if (game.getgameBoard()[neighbor] != -1) {
                type = ActionType.JUMP;
                destination = Helpers.getNeighbor(neighbor, direction);
            } else {
                type = ActionType.MOVE;
                destination = neighbor;
            }
        } catch (Exception e) {
            return null;
        }
        return new Action(type, origin, destination);
    }
}
package com.duckeverlasting;

import com.duckeverlasting.enums.Direction;

public class Helpers {
    private static boolean isAtLeftEdge(int origin) {
        return origin % 8 == 4;
    }

    private static boolean isAtRightEdge(int origin) {
        return origin % 8 == 3;
    }

    private static boolean isAtTop(int origin) {
        return origin < 4;
    }

    private static boolean isAtBottom(int origin) {
        return origin > 27;
    }

    public static int getNeighbor(int origin, Direction direction) {
        if (origin == -1) {
            return -1;
        }
        int offset = origin % 8 < 4 ? 1 : 0;
        int destination;
        switch (direction) {
            case UP_LEFT:
                if (isAtTop(origin) || isAtLeftEdge(origin)) {
                    destination = -1;
                } else {
                    destination = origin - 5 + offset;
                }
                break;
            case UP_RIGHT:
                if (isAtTop(origin) || isAtRightEdge(origin)) {
                    destination = -1;
                } else {
                    destination = origin - 4 + offset;
                }
                break;
            case DOWN_RIGHT:
                if (isAtBottom(origin) || isAtRightEdge(origin)) {
                    destination = -1;
                } else {
                    destination = origin + 4 + offset;
                }
                break;
            case DOWN_LEFT:
                if (isAtBottom(origin) || isAtLeftEdge(origin)) {
                    destination = -1;
                } else {
                    destination = origin + 3 + offset;
                }
                break;
            default:
                destination = -1;
        }
        return destination;
    }

    public static int getBetween(int origin, int destination) {
        switch (destination - origin) {
            case -9:
                return getNeighbor(origin, Direction.UP_LEFT);
            case -7:
                return getNeighbor(origin, Direction.UP_RIGHT);
            case 9:
                return getNeighbor(origin, Direction.DOWN_RIGHT);
            case 7:
                return getNeighbor(origin, Direction.DOWN_LEFT);
            default:
                return -1;
        }
    }
}

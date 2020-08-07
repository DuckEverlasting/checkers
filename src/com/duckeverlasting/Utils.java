package com.duckeverlasting;

import com.duckeverlasting.enums.Direction;

public class Utils
{
    public int look(int origin, Direction direction)
    {
        boolean offset = origin % 8 < 4;
        int destination;
        switch (direction)
        {
            case UP_LEFT ->
            if (isAtTop(origin) || isAtLeftEdge(origin))
            {
                return -1;
            } else
            {

            }
            destination = 0;
            case UP_RIGHT ->
                    destination = 0;
            case DOWN_RIGHT ->
                    destination = 0;
            case DOWN_LEFT ->
                    destination = 0;
            case TWO_UP_LEFT ->
                    destination = 0;
            case TWO_UP_RIGHT ->
                    destination = 0;
            case TWO_DOWN_RIGHT ->
                    destination = 0;
            case TWO_DOWN_LEFT ->
                    destination = 0;
            default -> destination = -1;
        }
        return destination;

    }

    private boolean isAtLeftEdge(int origin)
    {
        return origin % 4 == 0;
    }

    private boolean isAtRightEdge(int origin)
    {
        return origin % 4 == 3;
    }

    private boolean isAtTop(int origin)
    {
        return origin < 4;
    }

    private boolean isAtBottom(int origin)
    {
        return origin > 27;
    }

    public static int getUpLeft(int origin) {
        if (origin % 4 == 0 || origin < 4) {
            return -1;
        } else if (origin % 8 < 4) {
            return origin - 4;
        } else {
            return origin - 5;
        }
    }

    public static int getUpRight(int origin) {
        if (origin % 4 == 3 || origin < 4) {
            return -1;
        } else if (origin % 8 < 4) {
            return origin - 3;
        } else {
            return origin - 4;
        }
    }

    public static int getDownLeft(int origin) {
        if (origin % 4 == 0 || origin > 27) {
            return -1;
        } else if (origin % 8 < 4) {
            return origin + 4;
        } else {
            return origin + 3;
        }
    }

    public static int getDownRight(int origin) {
        if (origin % 4 == 3 || origin > 27) {
            return -1;
        } else if (origin % 8 < 4) {
            return origin + 5;
        } else {
            return origin + 4;
        }
    }
}

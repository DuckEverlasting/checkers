package com.duckeverlasting;

import com.duckeverlasting.enums.Direction;

public class Utils
{
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
    public static int getNeighbor(int origin, Direction direction)
    {
        if (origin == -1)
        {
            return -1;
        }
        int offset = origin % 8 < 4 ? 1 : 0;
        int destination;
        switch (direction)
        {
            case UP_LEFT ->
                if (isAtTop(origin) || isAtLeftEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin - 5 + offset;
                }
              destination = 0;
            case UP_RIGHT ->
                if (isAtTop(origin) || isAtRightEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin - 4 + offset;
                }
            case DOWN_RIGHT ->
                if (isAtBottom(origin) || isAtRightEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin + 4 + offset;
                }
            case DOWN_LEFT ->
                if (isAtBottom(origin) || isAtLeftEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin + 3 + offset;
                }
            default -> destination = -1;
        }
        return destination;

    }
}

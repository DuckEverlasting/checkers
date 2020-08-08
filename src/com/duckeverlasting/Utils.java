package com.duckeverlasting;

import com.duckeverlasting.enums.ActionType;
import com.duckeverlasting.enums.Direction;
import com.duckeverlasting.objects.Action;

public class Utils
{
    private static boolean isAtLeftEdge(int origin)
    {
        return origin % 4 == 0;
    }

    private static boolean isAtRightEdge(int origin)
    {
        return origin % 4 == 3;
    }

    private static boolean isAtTop(int origin)
    {
        return origin < 4;
    }

    private static boolean isAtBottom(int origin)
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
            case UP_LEFT:
                if (isAtTop(origin) || isAtLeftEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin - 5 + offset;
                }
                break;
            case UP_RIGHT:
                if (isAtTop(origin) || isAtRightEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin - 4 + offset;
                }
                break;
            case DOWN_RIGHT:
                if (isAtBottom(origin) || isAtRightEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin + 4 + offset;
                }
                break;
            case DOWN_LEFT:
                if (isAtBottom(origin) || isAtLeftEdge(origin))
                {
                    destination = -1;
                } else
                {
                    destination = origin + 3 + offset;
                }
                break;
            default: destination = -1;
        }
        return destination;
    }

    public static int getBetween(int origin, int destination)
    {
        if (destination + 7 == origin)
        {
            return getNeighbor(origin, Direction.UP_LEFT);
        } else if (destination + 9 == origin)
        {
            return getNeighbor(origin, Direction.UP_RIGHT);
        } else if (destination - 9 == origin)
        {
            return getNeighbor(origin, Direction.DOWN_RIGHT);
        } else if (destination - 7 == origin)
        {
            return getNeighbor(origin, Direction.DOWN_LEFT);
        }
        return -1;
    }
    
    public static Action parseInput(String input)
    {
        String[] array = input.split(" ");
        int origin;
        ActionType type;
        int destination;
        try
        {
            origin = Integer.parseInt(array[0]);
            if (array[1].equalsIgnoreCase("MOVE"))
            {
                type = ActionType.MOVE; 
            } else if (array[1].equalsIgnoreCase("JUMP"))
            {
                type = ActionType.MOVE;
            } else {
                throw new Exception("move or jump");
            }
            destination = Integer.parseInt(array[2]);
        }
        catch(Exception err)
        {
            System.out.println(err);
            String newInput = System.console().readLine("TRY AGAIN: ");
            return parseInput(newInput);
        }
        return new Action(type, origin, destination);
    }
}

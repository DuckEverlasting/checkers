package com.duckeverlasting.objects;

public class GamePiece
{
    int id;
    int team;
    int position;
    boolean isKing;

    public GamePiece(int id, int player, int position)
    {
        this.id = id;
        this.team = player;
        this.position = position;
    }

    public int getId()
    {
        return id;
    }

    public int getPlayer()
    {
        return team;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public boolean getIsKing()
    {
        return isKing;
    }

    public void setIsKing(boolean isKing)
    {
        this.isKing = isKing;
    }
}

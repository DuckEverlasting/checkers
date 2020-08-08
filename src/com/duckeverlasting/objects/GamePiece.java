package com.duckeverlasting.objects;

public class GamePiece
{
    int id;
    int player;
    int position;
    boolean king;

    public GamePiece(int id, int player, int position)
    {
        this.id = id;
        this.player = player;
        this.position = position;
    }

    public int getId()
    {
        return id;
    }

    public int getPlayer()
    {
        return player;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public boolean isKing()
    {
        return king;
    }

    public void setKing(boolean king)
    {
        this.king = king;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", player='" + getPlayer() + "'" +
            ", position='" + getPosition() + "'" +
            ", isKing='" + isKing() + "'" +
            "}";
    }
}

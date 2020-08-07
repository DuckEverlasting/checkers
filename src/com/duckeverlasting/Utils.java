package com.duckeverlasting;

public class Utils
{
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

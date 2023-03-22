package com.bear.effectivejava.item10.transitivity;

public class Point {
    private final float x;
    private final float y;

    public Point(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }
}

package com.bear.effectivejava.item10.transitivity;

import java.awt.*;

public class FixedColorPoint extends Point {
    private final Color color;

    public FixedColorPoint(final int x, final int y, final Color color) {
        super(x, y);
        this.color = color;
    }

    /**
     * 대칭성을 만족하도록 수정하였지만 전이성을 위배하게 된다.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        if (!(o instanceof FixedColorPoint)) {
            return o.equals(this);
        }
        return super.equals(o) && ((FixedColorPoint) o).color == color;
    }
}

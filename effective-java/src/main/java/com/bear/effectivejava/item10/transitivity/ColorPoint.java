package com.bear.effectivejava.item10.transitivity;

import java.awt.*;

public class ColorPoint extends Point {

    private final Color color;

    public ColorPoint(final int x, final int y, final Color color) {
        super(x, y);
        this.color = color;
    }

    /**
     * 대칭성을 위배한 equals, x.equals(y) 일 때 y.equals(x)여야 한다는 규칙을 위반하게 된다. superclass인
     * {@link Point} 클래스로 비교해보면 쉽게 알 수 있다.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint)) {
            return false;
        }
        return super.equals(o) && ((ColorPoint) o).color == color;
    }
}

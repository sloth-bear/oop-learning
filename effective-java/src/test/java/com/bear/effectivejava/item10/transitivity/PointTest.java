package com.bear.effectivejava.item10.transitivity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

public class PointTest {

    @Test
    void fixedSymmetryViolation_thenViolatedSymmetry() {
        // given
        final FixedColorPoint cp = new FixedColorPoint(1, 2, Color.BLACK);
        final Point p = new Point(1, 2);
        final FixedColorPoint cp2 = new FixedColorPoint(1, 2, Color.BLUE);

        // when
        final boolean result1 = cp.equals(p);
        final boolean result2 = p.equals(cp2);
        final boolean result3 = cp.equals(cp2);

        // then
        Assertions.assertTrue(result1);
        Assertions.assertTrue(result2);
        Assertions.assertFalse(result3);
    }

    @Test
    void fixedSymmetryViolation_thenStackOverflowError() {
        // given
        final FixedColorPoint cp = new FixedColorPoint(1, 2, Color.BLACK);
        final SmellPoint sp = new SmellPoint(1, 2, "Lemon");

        // when && then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> cp.equals(sp)).isInstanceOf(StackOverflowError.class);
    }

    static class SmellPoint extends Point {
        private final String smell;

        SmellPoint(final float x, final float y, final String smell) {
            super(x, y);
            this.smell = smell;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            if (!(o instanceof SmellPoint)) {
                return o.equals(this);
            }
            return super.equals(o) && Objects.equals(((SmellPoint) o).smell, smell);
        }
    }
}

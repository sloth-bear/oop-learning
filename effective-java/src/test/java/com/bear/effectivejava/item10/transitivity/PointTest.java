package com.bear.effectivejava.item10.transitivity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
}

package com.bear.effectivejava.item10.symmetry;

import com.bear.effectivejava.item10.transitivity.ColorPoint;
import com.bear.effectivejava.item10.transitivity.FixedColorPoint;
import com.bear.effectivejava.item10.transitivity.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PointTest {

    @Test
    void violationTest() {
        // given
        final Point p = new Point(1, 2);
        final ColorPoint cp = new ColorPoint(1, 2, Color.BLACK);

        // when
        final boolean result1 = p.equals(cp);
        final boolean result2 = cp.equals(p);

        // then
        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
    }

    @Test
    void fixedViolationTest() {
        // given
        final Point p = new Point(1, 2);
        final FixedColorPoint cp = new FixedColorPoint(1, 2, Color.BLACK);

        // when
        final boolean result1 = p.equals(cp);
        final boolean result2 = cp.equals(p);

        // then
        Assertions.assertTrue(result1);
        Assertions.assertTrue(result2);
    }

}

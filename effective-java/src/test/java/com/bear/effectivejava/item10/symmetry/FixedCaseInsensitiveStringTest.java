package com.bear.effectivejava.item10.symmetry;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FixedCaseInsensitiveStringTest {

    @Test
    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @DisplayName("Equals - 대칭성 위베 케이스 수정")
    void givenTwoAnotherStringClass_whenEquals_thenViolateSymmetric() {
        // given
        FixedCaseInsensitiveString fcis = new FixedCaseInsensitiveString("Polish");
        String polish = "polish";

        // when
        boolean result1 = fcis.equals(polish);
        boolean result2 = polish.equals(fcis);
        boolean result3 = fcis.equalsAsString(polish);

        // then
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isTrue();
    }
}
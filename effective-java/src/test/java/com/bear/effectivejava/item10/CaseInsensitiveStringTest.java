package com.bear.effectivejava.item10;

import com.bear.effectivejava.item10.symmetry.CaseInsensitiveString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CaseInsensitiveStringTest {

    @Test
    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @DisplayName("Equals - 대칭성 위베 케이스: String 유사한 클래스를 대소문자 구별을 무시하기 위해 만들었지만 대칭성 위배")
    void givenTwoAnotherStringClass_whenEquals_thenViolateSymmetric() {
        // given
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String polish = "polish";

        // when
        boolean result1 = cis.equals(polish);
        boolean result2 = polish.equals(cis);

        // then
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isFalse();
    }

}

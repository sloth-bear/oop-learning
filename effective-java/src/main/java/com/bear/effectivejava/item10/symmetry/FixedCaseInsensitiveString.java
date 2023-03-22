package com.bear.effectivejava.item10.symmetry;

import java.util.Objects;

public class FixedCaseInsensitiveString {
    private final String s;

    public FixedCaseInsensitiveString(final String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof FixedCaseInsensitiveString)
                && s.equalsIgnoreCase(((FixedCaseInsensitiveString) o).s);
    }

    public boolean equalsAsString(final String s) {
        return Objects.requireNonNull(s).equalsIgnoreCase(this.s);
    }

}

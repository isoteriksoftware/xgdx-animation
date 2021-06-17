package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

public class EqualCondition<T> implements ICondition {
    public DataSource<T> first, second;
    public boolean identity;

    public EqualCondition(DataSource<T> first, DataSource<T> second, boolean identity) {
        this.first = first;
        this.second = second;
        this.identity = identity;
    }

    public EqualCondition(DataSource<T> first, DataSource<T> second) {
        this(first, second, true);
    }

    public EqualCondition(T first, T second, boolean identity) {
        this(new DataSource<>(first), new DataSource<>(second), identity);
    }

    public EqualCondition(T first, T second) {
        this(first, second, true);
    }

    @Override
    public boolean test() {
        if (identity)
            return first.data == second.data;
        else
            return first.data.equals(second.data);
    }
}

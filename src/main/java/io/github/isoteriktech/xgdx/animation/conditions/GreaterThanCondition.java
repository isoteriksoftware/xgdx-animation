package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

public class GreaterThanCondition implements ICondition {
    public DataSource<Float> first, second;

    public GreaterThanCondition(DataSource<Float> first, DataSource<Float> second) {
        this.first = first;
        this.second = second;
    }

    public GreaterThanCondition(float first, float second) {
        this(new DataSource<>(first), new DataSource<>(second));
    }

    @Override
    public boolean test() {
        return first.data > second.data;
    }
}

package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

public class BooleanCondition implements ICondition  {
    public DataSource<Boolean> value;

    public  BooleanCondition(DataSource<Boolean> value) {
        this.value = value;
    }

    public BooleanCondition(boolean value) {
        this(new DataSource<>(value));
    }

    @Override
    public boolean test() {
        return value.data;
    }
}

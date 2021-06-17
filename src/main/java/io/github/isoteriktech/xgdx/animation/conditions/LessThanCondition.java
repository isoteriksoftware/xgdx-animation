package io.github.isoteriktech.xgdx.animation.conditions;

public class LessThanCondition extends GreaterThanCondition {
    public LessThanCondition(DataSource<Float> first, DataSource<Float> second) {
        super(first, second);
    }

    public LessThanCondition(float first, float second) {
        super(first, second);
    }

    @Override
    public boolean test() {
        return first.data < second.data;
    }
}

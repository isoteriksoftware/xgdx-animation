package io.github.isoteriktech.xgdx.animation.conditions;

public class NumericCompoundCondition extends CompoundCondition<Float> {
    public NumericCompoundCondition(DataSource<Float> dataSource) {
        super(dataSource);
    }

    public NumericCompoundCondition(Float dataSource) {
        super(dataSource);
    }

    public NumericCompoundCondition greaterThan(DataSource<Float> dataSource) {
        and(new GreaterThanCondition(this.dataSource, dataSource));
        return this;
    }

    public NumericCompoundCondition greaterThan(float value) {
        and(new GreaterThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }

    public NumericCompoundCondition lessThan(DataSource<Float> dataSource) {
        and(new LessThanCondition(this.dataSource, dataSource));
        return this;
    }

    public NumericCompoundCondition lessThan(float value) {
        and(new LessThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }
}

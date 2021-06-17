package io.github.isoteriktech.xgdx.animation.conditions;

public class NumericCompoundCondition extends CompoundCondition<Float> {
    public NumericCompoundCondition(DataSource<Float> dataSource) {
        super(dataSource);
    }

    public NumericCompoundCondition(Float initialValue) {
        super(initialValue);
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

    public NumericCompoundCondition orGreaterThan(DataSource<Float> dataSource) {
        or(new GreaterThanCondition(this.dataSource, dataSource));
        return this;
    }

    public NumericCompoundCondition orGreaterThan(float value) {
        or(new GreaterThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }

    public NumericCompoundCondition orLessThan(DataSource<Float> dataSource) {
        or(new LessThanCondition(this.dataSource, dataSource));
        return this;
    }

    public NumericCompoundCondition orLessThan(float value) {
        or(new LessThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }
}

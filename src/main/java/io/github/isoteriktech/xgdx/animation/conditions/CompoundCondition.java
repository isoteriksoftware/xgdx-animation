package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

public class CompoundCondition<T> implements ICondition {
    protected AndCondition andConditions;
    protected OrCondition orConditions;
    public DataSource<T> dataSource;

    public CompoundCondition(DataSource<T> dataSource) {
        this.dataSource = dataSource;
        orConditions = new OrCondition(new EqualCondition<>(dataSource, dataSource));
        andConditions = new AndCondition(orConditions);
    }

    public CompoundCondition(T dataSource) {
        this(new DataSource<>(dataSource));
    }

    public CompoundCondition<T> setDataSource(T dataSource) {
        this.dataSource.set(dataSource);
        return this;
    }

    public T getDataSource() {
        return dataSource.data;
    }

    public CompoundCondition<T> and(ICondition condition, ICondition... conditions) {
        andConditions.addCondition(condition, conditions);
        return this;
    }

    public CompoundCondition<T> or(ICondition condition, ICondition... conditions) {
        orConditions.addCondition(condition, conditions);
        return this;
    }

    public CompoundCondition<T> equal(DataSource<T> dataSource) {
        return and(new EqualCondition<>(this.dataSource, dataSource));
    }

    public CompoundCondition<T> notEqual(DataSource<T> dataSource) {
        return and(new NotEqualCondition<>(this.dataSource, dataSource));
    }

    public CompoundCondition<T> equal(T value) {
        return equal(new DataSource<>(value));
    }

    public CompoundCondition<T> notEqual(T value) {
        return notEqual(new DataSource<>(value));
    }

    @Override
    public boolean test() {
        return andConditions.test();
    }
}

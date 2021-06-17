package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

public class CompoundCondition<T> implements ICondition {
    protected ICondition delegate;
    public DataSource<T> value;

    public CompoundCondition(DataSource<T> value) {
        this.value = value;
        delegate = new EqualCondition<>(value, value);
    }

    public CompoundCondition(T value) {
        this(new DataSource<>(value));
    }

    public CompoundCondition<T> setValue(T value) {
        this.value.set(value);
        return this;
    }

    public T getValue() {
        return value.data;
    }

    public CompoundCondition<T> and(ICondition condition, ICondition... conditions) {
        ICondition current = delegate;
        delegate = new AndCondition(condition, conditions);
        ((AndCondition)delegate).addCondition(current);
        return this;
    }

    public CompoundCondition<T> or(ICondition condition, ICondition... conditions) {
        ICondition current = delegate;
        delegate = new OrCondition(condition, conditions);
        ((OrCondition)delegate).addCondition(current);
        return this;
    }

    public CompoundCondition<T> equal(ICondition condition) {
        delegate = new EqualCondition<>(delegate, condition);
        return this;
    }

    public CompoundCondition<T> notEqual(ICondition condition) {
        delegate = new NotEqualCondition<>(delegate, condition);
        return this;
    }

    public CompoundCondition<T> equal(T value) {
        delegate = new AndCondition(delegate,
                new EqualCondition<>(this.value.data, value));
        return this;
    }

    public CompoundCondition<T> equal(T value) {
        delegate = new AndCondition(delegate,
                new EqualCondition<>(this.value.data, value));
        return this;
    }

    @Override
    public boolean test() {
        return delegate.test();
    }
}

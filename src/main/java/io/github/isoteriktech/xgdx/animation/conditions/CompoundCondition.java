package io.github.isoteriktech.xgdx.animation.conditions;

import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.animation.ICondition;

/**
 * A condition that nests other conditions (child conditions) to create a complex condition.
 * @param <T> the type of object that conditions will use for comparisons when required
 *
 * @author isoterik
 */
public class CompoundCondition<T> extends AndCondition {
    /**
     * Child conditions that should be ORed
     */
    protected OrCondition orConditions;

    /**
     * The data source.
     */
    public DataSource<T> dataSource;

    /**
     * Creates a new instance given the data source
     * @param dataSource the data source
     */
    public CompoundCondition(DataSource<T> dataSource) {
        super(new Array<>());
        this.dataSource = dataSource;
        orConditions = new OrCondition(new EqualCondition<>(dataSource, dataSource));
        addCondition(orConditions);
    }

    /**
     * Creates a new instance given the initial value.
     * @param initialValue the initial value
     */
    public CompoundCondition(T initialValue) {
        this(new DataSource<>(initialValue));
    }

    /**
     * Wraps the given conditions inside an {@link AndCondition}.
     * @param condition a child condition
     * @param conditions more child conditions
     * @return this instance for chaining
     */
    public CompoundCondition<T> and(ICondition condition, ICondition... conditions) {
        addCondition(condition, conditions);
        return this;
    }

    /**
     * Wraps the given conditions inside an {@link OrCondition}.
     * @param condition a child condition
     * @param conditions more child conditions
     * @return this instance for chaining
     */
    public CompoundCondition<T> or(ICondition condition, ICondition... conditions) {
        orConditions.addCondition(condition, conditions);
        return this;
    }

    /**
     * Wraps the given conditions inside a {@link NotCondition}.
     * The condition will be ANDed.
     * @param condition a child condition
     * @return this instance for chaining
     */
    public CompoundCondition<T> not(ICondition condition) {
        return and(new NotCondition(condition));
    }

    /**
     * Wraps the given conditions inside a {@link NotCondition}.
     * The condition will be ORed.
     * @param condition a child condition
     * @return this instance for chaining
     */
    public CompoundCondition<T> orNot(ICondition condition) {
        return or(new NotCondition(condition));
    }

    /**
     * Adds a new {@link EqualCondition} using the given data source.
     * The condition will be ANDed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public CompoundCondition<T> equal(DataSource<T> dataSource) {
        return and(new EqualCondition<>(this.dataSource, dataSource));
    }

    /**
     * Adds a new {@link EqualCondition} using the given data source.
     * The condition will be ORed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public CompoundCondition<T> orEqual(DataSource<T> dataSource) {
        return or(new EqualCondition<>(this.dataSource, dataSource));
    }

    /**
     * Adds a new {@link NotEqualCondition} using the given data source.
     * The condition will be ANDed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public CompoundCondition<T> notEqual(DataSource<T> dataSource) {
        return and(new NotEqualCondition<>(this.dataSource, dataSource));
    }

    /**
     * Adds a new {@link NotEqualCondition} using the given data source.
     * The condition will be ORed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public CompoundCondition<T> orNotEqual(DataSource<T> dataSource) {
        return or(new NotEqualCondition<>(this.dataSource, dataSource));
    }

    /**
     * Adds a new {@link EqualCondition} using the given value.
     * The condition will be ANDed.
     * @param value the value.
     * @return this instance for chaining
     */
    public CompoundCondition<T> equal(T value) {
        return equal(new DataSource<>(value));
    }

    /**
     * Adds a new {@link EqualCondition} using the given value.
     * The condition will be ORed.
     * @param value the value.
     * @return this instance for chaining
     */
    public CompoundCondition<T> orEqual(T value) {
        return orEqual(new DataSource<>(value));
    }

    /**
     * Adds a new {@link NotEqualCondition} using the given value.
     * The condition will be ANDed.
     * @param value the value.
     * @return this instance for chaining
     */
    public CompoundCondition<T> notEqual(T value) {
        return notEqual(new DataSource<>(value));
    }

    /**
     * Adds a new {@link NotEqualCondition} using the given value.
     * The condition will be ORed.
     * @param value the value.
     * @return this instance for chaining
     */
    public CompoundCondition<T> orNotEqual(T value) {
        return orNotEqual(new DataSource<>(value));
    }
}

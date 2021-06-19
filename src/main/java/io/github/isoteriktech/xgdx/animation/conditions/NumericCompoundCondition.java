package io.github.isoteriktech.xgdx.animation.conditions;

/**
 * A condition for {@link Float}s that nests other conditions (child conditions) to create a complex condition.
 *
 * @author isoterik
 */
public class NumericCompoundCondition extends CompoundCondition<Float> {
    /**
     * Creates a new instance given the data source
     * @param dataSource the data source
     */
    public NumericCompoundCondition(DataSource<Float> dataSource) {
        super(dataSource);
    }

    /**
     * Creates a new instance given the initial value.
     * @param initialValue the initial value
     */
    public NumericCompoundCondition(Float initialValue) {
        super(initialValue);
    }

    /**
     * Adds a new {@link GreaterThanCondition} using the given data source.
     * The condition will be ANDed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public NumericCompoundCondition greaterThan(DataSource<Float> dataSource) {
        and(new GreaterThanCondition(this.dataSource, dataSource));
        return this;
    }

    /**
     * Adds a new {@link GreaterThanCondition} using the given value.
     * The condition will be ANDed.
     * @param value the value.
     * @return this instance for chaining
     */
    public NumericCompoundCondition greaterThan(float value) {
        and(new GreaterThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }

    /**
     * Adds a new {@link LessThanCondition} using the given data source.
     * The condition will be ANDed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public NumericCompoundCondition lessThan(DataSource<Float> dataSource) {
        and(new LessThanCondition(this.dataSource, dataSource));
        return this;
    }

    /**
     * Adds a new {@link LessThanCondition} using the given value.
     * The condition will be ANDed.
     * @param value the value.
     * @return this instance for chaining
     */
    public NumericCompoundCondition lessThan(float value) {
        and(new LessThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }

    /**
     * Adds a new {@link GreaterThanCondition} using the given data source.
     * The condition will be ORed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public NumericCompoundCondition orGreaterThan(DataSource<Float> dataSource) {
        or(new GreaterThanCondition(this.dataSource, dataSource));
        return this;
    }

    /**
     * Adds a new {@link GreaterThanCondition} using the given value.
     * The condition will be ORed.
     * @param value the value.
     * @return this instance for chaining
     */
    public NumericCompoundCondition orGreaterThan(float value) {
        or(new GreaterThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }

    /**
     * Adds a new {@link LessThanCondition} using the given data source.
     * The condition will be ORed.
     * @param dataSource the data source.
     * @return this instance for chaining
     */
    public NumericCompoundCondition orLessThan(DataSource<Float> dataSource) {
        or(new LessThanCondition(this.dataSource, dataSource));
        return this;
    }

    /**
     * Adds a new {@link LessThanCondition} using the given value.
     * The condition will be ORed.
     * @param value the value.
     * @return this instance for chaining
     */
    public NumericCompoundCondition orLessThan(float value) {
        or(new LessThanCondition(this.dataSource, new DataSource<>(value)));
        return this;
    }
}

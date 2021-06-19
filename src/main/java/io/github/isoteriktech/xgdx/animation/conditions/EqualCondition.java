package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

/**
 * A condition that compares two objects and evaluates to true only when the objects are equal.
 * @param <T> the type of object to compare
 *
 * @author isoterik
 */
public class EqualCondition<T> implements ICondition {
    /**
     * The data source.
     */
    public DataSource<T> first, second;

    /**
     * When true, == will be used for comparison, else {@link Object#equals(Object)} will be used.
     */
    public boolean identity;

    /**
     * Creates a new instance given the data sources of the objects to compare.
     * @param first the data source for the first object
     * @param second the data source for the second object
     * @param identity When true, == will be used for comparison, else {@link Object#equals(Object)} will be used.
     */
    public EqualCondition(DataSource<T> first, DataSource<T> second, boolean identity) {
        this.first = first;
        this.second = second;
        this.identity = identity;
    }

    /**
     * Creates a new instance given the data sources of the objects to compare.
     * {@link #identity} defaults to false.
     * @param first the data source for the first object
     * @param second the data source for the second object
     */
    public EqualCondition(DataSource<T> first, DataSource<T> second) {
        this(first, second, false);
    }

    /**
     * Creates a new instance given the objects to compare.
     * @param first the first object
     * @param second the second object
     * @param identity When true, == will be used for comparison, else {@link Object#equals(Object)} will be used.
     */
    public EqualCondition(T first, T second, boolean identity) {
        this(new DataSource<>(first), new DataSource<>(second), identity);
    }

    /**
     * Creates a new instance given the objects to compare.
     * {@link #identity} defaults to false.
     * @param first the first object
     * @param second the second object
     */
    public EqualCondition(T first, T second) {
        this(first, second, false);
    }

    @Override
    public boolean test() {
        if (identity)
            return first.data == second.data;
        else
            return first.data.equals(second.data);
    }
}

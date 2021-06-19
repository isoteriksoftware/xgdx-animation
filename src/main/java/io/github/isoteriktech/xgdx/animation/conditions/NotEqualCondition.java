package io.github.isoteriktech.xgdx.animation.conditions;

/**
 * A condition that compares two objects and evaluates to true only when the objects are not equal.
 * @param <T> the type of object to compare
 *
 * @author isoterik
 */
public class NotEqualCondition<T> extends EqualCondition<T> {
    /**
     * Creates a new instance given the data sources of the objects to compare.
     * @param first the data source for the first object
     * @param second the data source for the second object
     * @param identity When true, == will be used for comparison, else {@link Object#equals(Object)} will be used.
     */
    public NotEqualCondition(DataSource<T> first, DataSource<T> second, boolean identity) {
        super(first, second, identity);
    }

    /**
     * Creates a new instance given the data sources of the objects to compare.
     * {@link #identity} defaults to false.
     * @param first the data source for the first object
     * @param second the data source for the second object
     */
    public NotEqualCondition(DataSource<T> first, DataSource<T> second) {
        super(first, second);
    }

    /**
     * Creates a new instance given the objects to compare.
     * @param first the first object
     * @param second the second object
     * @param identity When true, == will be used for comparison, else {@link Object#equals(Object)} will be used.
     */
    public NotEqualCondition(T first, T second, boolean identity) {
        super(first, second, identity);
    }

    /**
     * Creates a new instance given the objects to compare.
     * {@link #identity} defaults to false.
     * @param first the first object
     * @param second the second object
     */
    public NotEqualCondition(T first, T second) {
        super(first, second);
    }

    @Override
    public boolean test() {
        return !super.test();
    }
}

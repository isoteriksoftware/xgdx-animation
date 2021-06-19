package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

/**
 * A condition that compares two {@link Float}s and evaluates to true only when the first is greater than the second.
 *
 * @author isoterik
 */
public class GreaterThanCondition implements ICondition {
    /**
     * The data source.
     */
    public DataSource<Float> first, second;

    /**
     * Creates a new instance given the data sources of the objects to compare.
     * @param first the data source for the first Float
     * @param second the data source for the second Float
     */
    public GreaterThanCondition(DataSource<Float> first, DataSource<Float> second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new instance given the floats to compare.
     * @param first the first float
     * @param second the second second
     */
    public GreaterThanCondition(float first, float second) {
        this(new DataSource<>(first), new DataSource<>(second));
    }

    @Override
    public boolean test() {
        return first.data > second.data;
    }
}

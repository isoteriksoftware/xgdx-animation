package io.github.isoteriktech.xgdx.animation.conditions;

/**
 * A condition that compares two {@link Float}s and evaluates to true only when the first is lesser than the second.
 *
 * @author isoterik
 */
public class LessThanCondition extends GreaterThanCondition {
    /**
     * Creates a new instance given the data sources of the objects to compare.
     * @param first the data source for the first Float
     * @param second the data source for the second Float
     */
    public LessThanCondition(DataSource<Float> first, DataSource<Float> second) {
        super(first, second);
    }

    /**
     * Creates a new instance given the floats to compare.
     * @param first the first float
     * @param second the second second
     */
    public LessThanCondition(float first, float second) {
        super(first, second);
    }

    @Override
    public boolean test() {
        return first.data < second.data;
    }
}

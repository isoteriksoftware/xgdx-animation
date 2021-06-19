package io.github.isoteriktech.xgdx.animation.conditions;

import io.github.isoteriktech.xgdx.animation.ICondition;

/**
 * A condition that evaluates to its current boolean value.
 *
 * @author isoterik
 */
public class BooleanCondition implements ICondition  {
    /**
     * The data source.
     */
    public DataSource<Boolean> dataSource;

    /**
     * Creates a new instance given the data source
     * @param dataSource the data source
     */
    public  BooleanCondition(DataSource<Boolean> dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new instance given the value.
     * @param value the value
     */
    public BooleanCondition(boolean value) {
        this(new DataSource<>(value));
    }

    @Override
    public boolean test() {
        return dataSource.data;
    }
}

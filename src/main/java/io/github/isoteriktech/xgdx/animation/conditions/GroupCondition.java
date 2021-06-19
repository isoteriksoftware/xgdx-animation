package io.github.isoteriktech.xgdx.animation.conditions;

import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.animation.ICondition;

/**
 * A condition that evaluates other conditions (child conditions).
 * Base classes must decide what to do with the child conditions.
 *
 * @author isoterik
 */
public abstract class GroupCondition implements ICondition {
    public Array<ICondition> conditions = new Array<>();

    /**
     * Creates a new instance given the child conditions to evaluate.
     * @param conditions the child conditions
     */
    public GroupCondition(Array<ICondition> conditions) {
        this.conditions = conditions;
    }

    /**
     * Creates a new instance given one or more child conditions.
     * @param condition a child condition
     * @param conditions more child conditions
     */
    public GroupCondition(ICondition condition, ICondition... conditions) {
        addCondition(condition, conditions);
    }

    /**
     * Adds one or more child conditions.
     * @param condition a child condition
     * @param conditions more child conditions
     */
    public void addCondition(ICondition condition, ICondition... conditions) {
        this.conditions.add(condition);
        this.conditions.addAll(conditions);
    }

    /**
     * Removes a child condition
     * @param condition the condition to remove
     */
    public void removeCondition(ICondition condition) {
        this.conditions.removeValue(condition, true);
    }

    /**
     * Checks if a condition is among the child conditions for this parent condition
     * @param condition the condition to check
     * @return true if it exists, false otherwise
     */
    public boolean hasCondition(ICondition condition) {
        return conditions.contains(condition, true);
    }
}

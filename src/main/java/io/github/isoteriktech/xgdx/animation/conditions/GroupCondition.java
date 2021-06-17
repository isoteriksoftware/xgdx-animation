package io.github.isoteriktech.xgdx.animation.conditions;

import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.animation.ICondition;

public abstract class GroupCondition implements ICondition {
    public Array<ICondition> conditions = new Array<>();

    public GroupCondition(Array<ICondition> conditions) {
        this.conditions = conditions;
    }

    public GroupCondition(ICondition condition, ICondition... conditions) {
        addCondition(condition, conditions);
    }

    public void addCondition(ICondition condition, ICondition... conditions) {
        this.conditions.add(condition);
        this.conditions.addAll(conditions);
    }

    public void removeCondition(ICondition condition) {
        this.conditions.removeValue(condition, true);
    }

    public boolean hasCondition(ICondition condition) {
        return conditions.contains(condition, true);
    }
}

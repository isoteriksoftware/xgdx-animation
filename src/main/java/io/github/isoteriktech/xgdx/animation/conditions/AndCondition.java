package io.github.isoteriktech.xgdx.animation.conditions;

import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.animation.ICondition;

public class AndCondition extends GroupCondition {
    public AndCondition(Array<ICondition> conditions) {
        super(conditions);
    }

    public AndCondition(ICondition condition, ICondition... conditions) {
        super(condition, conditions);
    }

    @Override
    public boolean test() {
        if (conditions.isEmpty())
            return false;

        for (ICondition condition : conditions) {
            if (!condition.test())
                return false;
        }

        return true;
    }
}

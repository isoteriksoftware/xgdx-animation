package io.github.isoteriktech.xgdx.animation.conditions;

import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.animation.ICondition;

public class OrCondition extends GroupCondition {
    public OrCondition(Array<ICondition> conditions) {
        super(conditions);
    }

    public OrCondition(ICondition condition, ICondition... conditions) {
        super(condition, conditions);
    }

    @Override
    public boolean test() {
        for (ICondition condition : conditions) {
            if (condition.test())
                return true;
        }

        return false;
    }
}

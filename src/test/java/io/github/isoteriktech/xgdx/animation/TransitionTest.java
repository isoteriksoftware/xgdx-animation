package io.github.isoteriktech.xgdx.animation;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import io.github.isoteriktech.xgdx.animation.conditions.BooleanCondition;
import io.github.isoteriktech.xgdx.animation.conditions.NumericCompoundCondition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransitionTest {
    State stateA = new State() {
        @Override
        public void enter(Object entity) {

        }

        @Override
        public void update(Object entity) {

        }

        @Override
        public void exit(Object entity) {

        }

        @Override
        public boolean onMessage(Object entity, Telegram telegram) {
            return false;
        }
    };
    State stateB = new State() {
        @Override
        public void enter(Object entity) {

        }

        @Override
        public void update(Object entity) {

        }

        @Override
        public void exit(Object entity) {

        }

        @Override
        public boolean onMessage(Object entity, Telegram telegram) {
            return false;
        }
    };

    @Test
    public void triggerTest() {
        NumericCompoundCondition numericCompoundCondition = new NumericCompoundCondition(3f);
        numericCompoundCondition.greaterThan(2f)
                .lessThan(4f)
                .greaterThan(1.5f);

        Transition transition = new Transition(stateA, stateB, numericCompoundCondition);

        assertTrue(transition.isTriggered());

        numericCompoundCondition.dataSource.set(0f);
        assertFalse(transition.isTriggered());
    }

    @Test
    public void reversedTransitionTest() {
        BooleanCondition booleanCondition = new BooleanCondition(false);

        Transition transition = new Transition(stateA, stateB, booleanCondition);

        assertFalse(transition.isTriggered());

        Transition reversedTransition = transition.invert();
        assertTrue(reversedTransition.isTriggered());

        assertSame(stateA, reversedTransition.to);
    }
}




















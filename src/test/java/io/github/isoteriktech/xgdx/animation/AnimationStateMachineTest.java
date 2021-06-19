package io.github.isoteriktech.xgdx.animation;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import io.github.isoteriktech.xgdx.animation.conditions.BooleanCondition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimationStateMachineTest {
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
    State stateC = new State() {
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
    public void currentStateTest() {
        BooleanCondition booleanCondition = new BooleanCondition(false);

        Transition transition = new Transition(stateA, stateB, booleanCondition);

        AnimationStateMachine stateMachine = new AnimationStateMachine();

        stateMachine.setInitialState(stateA);
        stateMachine.addTransition(transition);
        stateMachine.update();

        assertSame(stateA, stateMachine.getCurrentState());

        booleanCondition.dataSource.set(true);
        stateMachine.update();

        assertSame(stateB, stateMachine.getCurrentState());
    }

    @Test
    public void multipleTransitionTest(){
        BooleanCondition booleanCondition1 = new BooleanCondition(false);
        BooleanCondition booleanCondition2 = new BooleanCondition(true);

        Transition transitionAtoB = new Transition(stateA, stateB, booleanCondition1);
        Transition transitionBtoA = transitionAtoB.invert();
        Transition transitionBtoC = new Transition(stateB, stateC, booleanCondition2);

        AnimationStateMachine stateMachine = new AnimationStateMachine();

        stateMachine.setInitialState(stateA);
        stateMachine.addTransitions(transitionAtoB, transitionBtoA, transitionBtoC);
        stateMachine.update();

        assertSame(stateA, stateMachine.getCurrentState());

        // Intent: transition from A to B
        booleanCondition1.dataSource.set(true);
        stateMachine.update();

        /*
         What should happen: the current state should be C, and not B because the transition to C from B is triggered.
         Since that transition comes last, it will win.
         Note: the transition from A to B is also triggered but it will not be the final used transition because the next
         transition is also triggered.
        */
        assertSame(stateC, stateMachine.getCurrentState());

        // Intent: transition from C to B
        booleanCondition2.dataSource.set(false);
        stateMachine.addTransition(transitionBtoC.invert()); // to make the transition bi-directional
        stateMachine.update();

        assertSame(stateB, stateMachine.getCurrentState());
    }
}




















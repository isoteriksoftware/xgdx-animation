package io.github.isoteriktech.xgdx.animation;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.utils.Array;

/**
 * A <strong>Transition</strong> is used by {@link Animator} to change state.
 * It uses one or more {@link ICondition}s to determine when the change should happen. A transition will only take effect when all the available {@link ICondition}s
 * are met.
 * @param <S> the type of {@link State} that will be transitioned.
 *
 * @author isoteriksoftware
 * @see Animator
 * @see ICondition
 * @see State
 */
public class Transition<S extends State<?>> {
    protected Array<ICondition> conditions;

    /**
     * The state to transition from. This can be null.
     * When null, transition will be triggered from any state.
     */
    public S from;

    /**
     * The state to transition to. Should not be null.
     */
    public S to;

    /**
     * Creates a new instance with the states to transition between and an {@link Array} of conditions.
     * @param from the state to transition from. Can be null. If it is null, transition will be from any state.
     * @param to the state to transition to.
     * @param conditions an array of conditions for this transition to take effect
     */
    public Transition(S from, S to, Array<ICondition> conditions) {
        this.from = from;
        this.to = to;
        this.conditions = conditions;
    }

    /**
     * Creates a new instance with the states to transition between and zero or more conditions.
     * <strong>Note:</strong> no transition will occur if no condition is provided. Conditions can always be added later
     * after construction.
     * @param from the state to transition from. Can be null. If it is null, transition will be from any state.
     * @param to the state to transition to.
     * @param conditions zero or more conditions
     */
    public Transition(S from, S to, ICondition... conditions) {
        this(from, to, Array.with(conditions));
    }

    /**
     * Creates a new instance with a state to transition to and an {@link Array} of conditions.
     * This will effectively transition from any state until {@link #from} is set!
     * @param to the state to transition to.
     * @param conditions an array of conditions for this transition to take effect
     */
    public Transition(S to, Array<ICondition> conditions) {
        this(null, to, conditions);
    }

    /**
     * Creates a new instance with a state to transition to and zero or more conditions.
     * This will effectively transition from any state until a {@link #from} is set!
     * <strong>Note:</strong> no transition will occur if no condition is provided. Conditions can always be added later after construction.
     * @param to the state to transition to.
     * @param conditions zero or more conditions
     */
    public Transition(S to, ICondition... conditions) {
        this(to, Array.with(conditions));
    }

    /**
     * Sets the {@link Array} of conditions for this transition.
     * @param conditions the array of conditions
     * @return this instance for chaining
     */
    public Transition<S> setConditions(Array<ICondition> conditions) {
        this.conditions = conditions;
        return this;
    }

    /**
     * @return the array of conditions for this transition
     */
    public Array<ICondition> getConditions() {
        return conditions;
    }

    /**
     * Adds a condition for this transition
     * @param condition the condition
     * @return this instance for chaining
     */
    public Transition<S> addCondition(ICondition condition) {
        conditions.add(condition);
        return this;
    }

    /**
     * Removes a condition
     * @param condition the condition
     * @return this instance for chaining
     */
    public Transition<S> removeCondition(ICondition condition) {
        conditions.removeValue(condition, true);
        return this;
    }

    /**
     * Checks if a given condition exists for this transition
     * @param condition the given condition
     * @return {@code true} if condition exists, {@code false} otherwise
     */
    public boolean hasCondition(ICondition condition) {
        return conditions.contains(condition, true);
    }

    /**
     * Creates a new instance capable of doing the exact opposite of what the current instance does. This is useful for creating bi-directional transitions.
     * For example: if this instance represents a transition from <em>StateA</em> to <em>StateB</em>, calling this method returns a new instance that transition from StateB back to StateA.
     *
     * <strong>Note:</strong>
     * <ul>
     *     <li>
     *         The returned instance represents a reversed copy of the current instance at the moment when this method is called. Subsequent changes to the original
     * 	       instance will have no effect on the returned instance.
     *     </li>
     *     <li>
     *         The conditions are also reversed!
     *     </li>
     * </ul>
     * @return a new instance that reverses the behavior of the current instance
     */
    public Transition<S> invert() {
        return new Transition<S>(to, from, conditions) {
            @Override
            public boolean isTriggered() {
                return !super.isTriggered();
            }
        };
    }

    /**
     * Creates a new instance that will transition back to the given transition instance from any state. This is useful for creating bi-directional transitions for
     * instances that have no valid {@link #from}(ie. it is a transition from any state).
     * <strong>Note:</strong>
     * <ul>
     *     <li>
     *         The returned instance represents a reversed copy of the current instance at the moment when this method is called. Subsequent changes to the original
     * 	       instance will have no effect on the returned instance.
     *     </li>
     *     <li>
     *         The conditions are also reversed!
     *     </li>
     * </ul>
     * @param toState the state to transition to.
     * @return a new instance that reverses the behavior of the current instance
     */
    public Transition<S> invert(S toState) {
        return new Transition<S>(null, toState, conditions) {
            @Override
            public boolean isTriggered() {
                return !super.isTriggered();
            }
        };
    }

    /**
     * Determines whether this transition should take effect or not.
     * @return {@code true} if all the conditions are met, {@code false} otherwise
     */
    public boolean isTriggered() {
        if (conditions.isEmpty())
            return false;

        for (ICondition condition : conditions) {
            if (!condition.test())
                return false;
        }

        return true;
    }
}

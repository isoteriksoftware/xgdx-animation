package io.github.isoteriktech.xgdx.animation;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.utils.Array;

/**
 * An <strong>AnimationStateMachine</strong> uses {@link Transition}s to automatically manage transitions between states.
 * The state machine uses available {@link Transition}s to determine the current state and the next state.
 * <p>
 * Because it is a <strong>finite state machine</strong>, in addition to the global state, this machine can only be in one state at any given time. {@link Transition}s are
 * evaluated from first to last. If more than one {@link Transition} is triggered, the last triggered (in the order added) transition will determine the current state, others are ignored!
 * <p>
 * This state machine does not allow explicit change of states (except the global and initial state). If you need more control, use {@link DefaultStateMachine} instead.
 * @param <E> the type of the entity handled by this state machine
 * @param <S> the type of state
 *
 * @see com.badlogic.gdx.ai.fsm.StateMachine
 * @see Transition
 *
 * @author isoteriksoftware
 */
public class AnimationStateMachine<E, S extends State<E>> extends DefaultStateMachine<E, S> {
    protected Array<Transition<S>> transitions;

    /**
     * Creates a new instance given an owner, initial state, global state and an {@link Array} of transitions
     * @param owner the owner of this state machine
     * @param initialState the initial state
     * @param globalState the global state
     * @param transitions an array of transitions
     */
    public AnimationStateMachine(E owner, S initialState, S globalState,
                                 Array<Transition<S>> transitions) {
        super(owner, initialState, globalState);
        this.transitions = transitions;
    }

    /**
     * Creates a new instance given an owner, initial state, global state and no transitions
     * @param owner the owner of this state machine
     * @param initialState the initial state
     * @param globalState the global state
     */
    public AnimationStateMachine(E owner, S initialState, S globalState) {
        this(owner, initialState, globalState, new Array<>());
    }

    /**
     * Creates a new instance given an owner and an initial state.
     * @param owner the owner of this state machine
     * @param initialState the initial state
     */
    public AnimationStateMachine(E owner, S initialState) {
        this(owner, initialState, null);
    }

    /**
     * Creates a new instance given an owner only.
     * @param owner the owner of this state machine
     */
    public AnimationStateMachine(E owner) {
        this(owner, null, null);
    }

    /**
     * Creates a new instance with no owner, initial state, global state or transitions
     */
    public AnimationStateMachine() {
        this(null, null, null);
    }

    /**
     * Sets the transitions for this state machine.
     * This will override the current transitions. If you want to add to the current array instead use {@link #addTransition(Transition)}
     * @param transitions the array of transitions
     */
    public void setTransitions(Array<Transition<S>> transitions) {
        this.transitions = transitions;
    }

    /**
     * @return the array of transitions for this state machine
     */
    public Array<Transition<S>> getTransitions() {
        return transitions;
    }

    /**
     * Adds a transition.
     * @param transition the transition to add.
     * @return the current instance for chaining.
     */
    public AnimationStateMachine<E, S> addTransition(Transition<S> transition) {
        transitions.add(transition);
        return this;
    }

    /**
     * Adds one or more transitions.
     * @param transition a transition to add.
     * @param transitions more transitions to add
     * @return the current instance for chaining.
     */
    @SafeVarargs
    public final AnimationStateMachine<E, S> addTransitions(Transition<S> transition, Transition<S>... transitions) {
        this.transitions.add(transition);
        this.transitions.addAll(transitions);
        return this;
    }

    /**
     * Removes a transition if it exists
     * @param transition the transition to remove
     * @return {@code true} if the given transition was removed, {@code false} otherwise
     */
    public boolean removeTransition(Transition<S> transition) {
        return transitions.removeValue(transition, true);
    }

    /**
     * Checks if a given transition exists for this state machine
     * @param transition the transition to check
     * @return {@code true} if the given transition exists, {@code false} otherwise
     */
    public boolean hasTransition(Transition<S> transition) {
        return transitions.contains(transition, true);
    }

    /** {@inheritDoc} */
    @Override
    public S getCurrentState() {
        return super.getCurrentState();
    }

    /** {@inheritDoc} */
    @Override
    public S getGlobalState() {
        return super.getGlobalState();
    }

    /** {@inheritDoc} */
    @Override
    public S getPreviousState() {
        return super.getPreviousState();
    }

    /**
     * This updates the state machine. The current state will be determined using available {@link Transition}s.
     * {@link Transition}s are evaluated in the order in which they were added, first to last.
     */
    @Override
    public void update() {
        // Update the global state if any
        if (globalState != null)
            globalState.update(owner);

        // Check for any transition
        // The last transition to be triggered always wins
        for (Transition<S> transition : transitions) {
            boolean triggered = transition.isTriggered();

            if (triggered && !isInState(transition.to)) {
                if (transition.from != null && !isInState(transition.from))
                    continue;

                previousState = currentState;
                currentState = transition.to;
            }
        }

        // Update the current state if any
        if (currentState != null)
            currentState.update(owner);
    }

    /**
     * This state machine does not support explicit change of state.
     * Do not call this method!
     */
    @Override
    public void changeState(S newState) {
        throw new UnsupportedOperationException();
    }

    /**
     * This state machine does not support explicit change of state.
     * Do not call this method!
     */
    @Override
    public boolean revertToPreviousState() {
        throw new UnsupportedOperationException();
    }
}

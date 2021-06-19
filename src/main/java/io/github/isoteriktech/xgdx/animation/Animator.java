package io.github.isoteriktech.xgdx.animation;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.Component;
import io.github.isoteriktech.xgdx.GameObject;

/**
 * An Animator can be attached to a {@link io.github.isoteriktech.xgdx.GameObject} to coordinate and switch animations
 * automatically using {@link Transition}s. States for {@link io.github.isoteriktech.xgdx.GameObject}s are considered
 * valida animations.
 * <p>
 * It uses a {@link AnimationStateMachine} internally to determine the previous animation played, the current animation
 * and the next animation to be played.
 * States (animations) cannot be changed directly. To change state (animation), the animator uses {@link Transition}s.
 *
 * @param <S> the type of state managed by this state machine
 *
 * @see AnimationStateMachine
 * @see FrameAnimation
 * @see Transition
 *
 * @author isoteriksoftware
 */
public class Animator<S extends State<GameObject>> extends Component {
    protected Array<S> animations;

    protected AnimationStateMachine<GameObject, S> stateMachine;

    protected S initialAnimation;

    // This objects holds transitions that were added before the component is attached
    // At that point, no GameObject exists and the StateMachine is uninitialized
    private final Array<Transition<S>> scheduledTransitions
            = new Array<>();

    /**
     * Creates a new instance given an initial animation and zero or more other animations
     * @param initialAnimation the initial animation
     * @param otherAnimations other animations
     */
    @SafeVarargs
    public Animator(S initialAnimation,
                    S... otherAnimations) {
        this.initialAnimation = initialAnimation;

        animations = new Array<>();
        animations.add(initialAnimation);
        animations.addAll(otherAnimations);
    }

    /**
     *
     * @return the {@link AnimationStateMachine} used by this animator
     */
    public AnimationStateMachine<GameObject, S> getStateMachine()
    { return stateMachine; }

    @Override
    public void attach() {
		/*
		Because we need a GameObject to animate, we waited till this instance is attached before initializing the state machine
		 */
        stateMachine = new AnimationStateMachine<>(
                gameObject, initialAnimation);

        // check if there are any scheduled transitions.
        // scheduled transitions are transitions added before this instance is attached to a GameObject
        for (Transition<S> transition : scheduledTransitions) {
            stateMachine.addTransition(transition);
        }
        scheduledTransitions.clear();
    }

    @Override
    public void detach() {
        // Once this is detached, we no longer have a valid GameObject to animate.
        stateMachine = null;
    }

    @Override
    public void update(float deltaTime) {
        // Update the state machine
        // This will effectively change (switch) animation if any transition is triggered
        stateMachine.update();
    }

    /**
     * Checks whether the given animation is one of the animations managed by this animator
     * @param animation the animation to check
     * @return true if found, false otherwise
     */
    public boolean hasAnimation(S animation)
    { return animations.contains(animation, true); }

    /**
     * Adds an animation to the list of animations that this animator manages. This has no effect if the animation was added before
     * @param animation the animation to add
     * @return this instance for chaining
     */
    public Animator<S> addAnimation(S animation) {
        if (!hasAnimation(animation))
            animations.add(animation);

        return this;
    }

    /**
     * Removes an animation from the list of animations that this animator manages. This has no effect if the animation does not exist
     * @param animation the animation to remove
     * @return this instance for chaining
     */
    public Animator<S> removeAnimation(S animation) {
        if (hasAnimation(animation)) {
            animations.removeValue(animation, true);

            // Remove all transitions to or from this animation
            for (Transition<S> transition : stateMachine.getTransitions()) {
                if (transition.from == animation || transition.to == animation)
                    stateMachine.removeTransition(transition);
            }
        }

        return this;
    }

    /**
     * Adds a transition.
     * <strong>Note:</strong> this will fail if the {@link Transition} is for animation(s) that are not yet added to the list of animations managed by this animator
     * @param transition the transition to add
     * @param isBidirectional if true and {@link Transition#from} is not null, a reversed transition will also be added
     * @throws IllegalArgumentException if the {@link Transition} is for animation(s) that are not yet added to the list of animations managed by this animator
     * @return this instance for chaining
     */
    public Animator<S> addTransition(Transition<S> transition, boolean isBidirectional) throws IllegalArgumentException {
        if (!hasAnimation(transition.from) &&
                !hasAnimation(transition.to)) {
            throw new IllegalArgumentException("None of the animations for this transition is managed by this animator!");
        }

        if (stateMachine != null) {
            stateMachine.addTransition(transition);
            if (isBidirectional && transition.from != null)
                stateMachine.addTransitions(transition.invert());
        }
        else {
            scheduledTransitions.add(transition);
            if (isBidirectional && transition.from != null)
                scheduledTransitions.add(transition.invert());
        }

        return this;
    }

    /**
     * Adds a transition.
     * <strong>Note:</strong> this will fail if the {@link Transition} is for animation(s) that are not yet added to the list of animations managed by this animator
     * @param transition the transition to add
     * @throws IllegalArgumentException if the {@link Transition} is for animation(s) that are not yet added to the list of animations managed by this animator
     * @return this instance for chaining
     */
    public Animator<S> addTransition(Transition<S> transition) throws IllegalArgumentException {
        return addTransition(transition, false);
    }

    /**
     * Adds a transition given an animation to transition from, an animation to transition to and one or more conditions for the transition.
     * <strong>Note:</strong> this will fail if either of the animations given is not yet added to the list of animations managed by this animator
     * @param from the animation to transition from. Can be null. If it is null, transition will be from any state.
     * @param to the animation to transition to
     * @param isBidirectional if true and {@link Transition#from} is not null, a reversed transition will also be added
     * @param condition a condition for the transition
     * @param conditions more conditions for the transition
     * @return this instance for chaining
     * @throws IllegalArgumentException if either of the animations given is not yet added to the list of animations managed by this animator
     */
    public Animator<S> addTransition(S from, S to, boolean isBidirectional,
                                  ICondition condition, ICondition... conditions) throws IllegalArgumentException {
        Array<ICondition> conditionArray = new Array<>();
        conditionArray.add(condition);
        conditionArray.addAll(conditions);
        return addTransition(new Transition<>(from, to, conditionArray), isBidirectional);
    }

    /**
     * Adds a transition given an animation to transition from, an animation to transition to and one or more conditions for the transition.
     * <strong>Note:</strong> this will fail if either of the animations given is not yet added to the list of animations managed by this animator
     * @param from the animation to transition from. Can be null. If it is null, transition will be from any state.
     * @param to the animation to transition to
     * @param condition a condition for the transition
     * @param conditions more conditions for the transition
     * @return this instance for chaining
     * @throws IllegalArgumentException if either of the animations given is not yet added to the list of animations managed by this animator
     */
    public Animator<S> addTransition(S from, S to,
                                     ICondition condition, ICondition... conditions) throws IllegalArgumentException {
        Array<ICondition> conditionArray = new Array<>();
        conditionArray.add(condition);
        conditionArray.addAll(conditions);
        return addTransition(new Transition<>(from, to, conditionArray), false);
    }

    /**
     * Adds a transition from any animation state given an animation to transition to.
     * <strong>Note:</strong> this will fail if the animation given is not yet added to the list of animations managed by this animator
     * @param to the animation to transition to
     * @param condition a condition for the transition
     * @param conditions more conditions for the transition
     * @return this instance for chaining
     * @throws IllegalArgumentException if the animation given is not yet added to the list of animations managed by this animator
     */
    public Animator<S> addTransition(S to,
                                  ICondition condition, ICondition... conditions) throws IllegalArgumentException {
        Array<ICondition> conditionArray = new Array<>();
        conditionArray.add(condition);
        conditionArray.addAll(conditions);
        return addTransition(new Transition<>(null, to, conditionArray), false);
    }

    /**
     * Removes a transition.
     * @param transition the transition to remove
     * @return this instance for chaining
     */
    public Animator<S> removeTransition(Transition<S> transition) {
        stateMachine.removeTransition(transition);
        return this;
    }

    /**
     * Returns the current animation.
     * @return the current animation. or null if the state machine is not initialized yet.
     */
    public S getCurrentAnimation() {
        if (stateMachine == null)
            return null;

        return stateMachine.getCurrentState();
    }

    /**
     * Returns the previous animation.
     * @return the previous animation. or null if the state machine is not initialized yet.
     */
    public S getPreviousAnimation() {
        if (stateMachine == null)
            return null;

        return stateMachine.getPreviousState();
    }

    /**
     * Returns the global animation. or null if the state machine is not initialized yet.
     * @return the global animation.
     */
    public S getGlobalAnimation() {
        if (stateMachine == null)
            return null;

        return stateMachine.getGlobalState();
    }

    /**
     * Sets the global animation.
     * @param globalAnimation the global animation.
     */
    public void setGlobalAnimation(S globalAnimation) {
        if (stateMachine == null)
            return;

        stateMachine.setGlobalState(globalAnimation);
    }

    /**
     *
     * @return the transitions managed by this animator
     */
    public Array<Transition<S>> getTransitions() {
		/*
		If the state machine is not initialized yet it means this instance is not attached so we simply return the scheduled transitions.
		 */
        if (stateMachine != null)
            return stateMachine.getTransitions();

        return scheduledTransitions;
    }
}

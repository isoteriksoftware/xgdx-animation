package io.github.isoteriktech.xgdx.animation;

/**
 * {@link ICondition}s are used to trigger a {@link Transition}.
 * A condition can use instances of {@link DataSource} to hold references to data used internally.
 * This makes it very easy to modify the internal data that conditions use.
 * <p>
 * An implementation of this interface must define its behavior inside the {@link #test()} method. This method should
 * return true only when the condition is satisfied.
 *
 * @author isoterik
 */
public interface ICondition {
    /**
     * Should return true if the condition is satisfied, false otherwise.
     * @return true if the condition is satisfied, false otherwise.
     */
    boolean test();

    /**
     * A {@link DataSource} holds a reference to an object used by {@link ICondition}s.
     * The referenced object can be changed using the reference.
     *
     * @param <T> the type of object to reference
     *
     * @author isoterik
     */
    class DataSource<T> {
        /**
         * The referenced data. This can be modified.
         */
        public T data;

        /**
         * Creates a new instance given the initial value.
         * @param data the initial value
         */
        public DataSource(T data) {
            this.data = data;
        }

        /**
         * Changes the referenced data.
         * @param data the new data
         */
        public void set(T data) {
            this.data = data;
        }
    }
}

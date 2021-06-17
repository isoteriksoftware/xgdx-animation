package io.github.isoteriktech.xgdx.animation;

public interface ICondition {
    boolean test();

    class DataSource<T> {
        public T data;

        public DataSource(T data) {
            this.data = data;
        }

        public void set(T data) {
            this.data = data;
        }
    }
}

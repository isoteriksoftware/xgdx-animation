package io.github.isoteriktech.xgdx.animation.conditions;

public class NotEqualCondition<T> extends EqualCondition<T> {
    public NotEqualCondition(DataSource<T> first, DataSource<T> second, boolean identity) {
        super(first, second, identity);
    }

    public NotEqualCondition(DataSource<T> first, DataSource<T> second) {
        super(first, second);
    }

    public NotEqualCondition(T first, T second, boolean identity) {
        super(first, second, identity);
    }

    public NotEqualCondition(T first, T second) {
        super(first, second);
    }

    @Override
    public boolean test() {
        return !super.test();
    }
}

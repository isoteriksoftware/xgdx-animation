package io.github.isoteriktech.xgdx.animation;

import io.github.isoteriktech.xgdx.animation.conditions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConditionTest {
    @Test
    public void booleanConditionTest() {
        BooleanCondition booleanCondition = new BooleanCondition(true);
        assertTrue(booleanCondition.test());

        booleanCondition.dataSource.set(false);
        assertFalse(booleanCondition.test());
    }

    @Test
    public void equalConditionTest() {
        EqualCondition<String> equalCondition = new EqualCondition<>("abc", "abc", true);
        assertTrue(equalCondition.test());

        equalCondition.identity = false;
        assertTrue(equalCondition.test());

        String test = "test";
        EqualCondition<String> equalCondition2 = new EqualCondition<>(test, test, true);
        assertTrue(equalCondition2.test());

        equalCondition2.identity = false;
        assertTrue(equalCondition2.test());

        EqualCondition<Float> equalCondition3 = new EqualCondition<>(1f, 1f, true);
        assertFalse(equalCondition3.test());

        equalCondition3.identity = false;
        assertTrue(equalCondition3.test());
    }

    @Test
    public void andConditionTest() {
        AndCondition andCondition1 = new AndCondition(new BooleanCondition(true), new BooleanCondition(false));
        assertFalse(andCondition1.test());

        AndCondition andCondition2 = new AndCondition(new BooleanCondition(false), new BooleanCondition(false));
        assertFalse(andCondition2.test());

        AndCondition andCondition3 = new AndCondition(new BooleanCondition(true), new BooleanCondition(true));
        assertTrue(andCondition3.test());
    }

    @Test
    public void orConditionTest() {
        OrCondition orCondition1 = new OrCondition(new BooleanCondition(true), new BooleanCondition(false));
        assertTrue(orCondition1.test());

        OrCondition orCondition2 = new OrCondition(new BooleanCondition(false), new BooleanCondition(false));
        assertFalse(orCondition2.test());

        OrCondition orCondition3 = new OrCondition(new BooleanCondition(true), new BooleanCondition(true));
        assertTrue(orCondition3.test());
    }

    @Test
    public void greaterThanConditionTest() {
        GreaterThanCondition greaterThanCondition = new GreaterThanCondition(1f, 1f);
        assertFalse(greaterThanCondition.test());

        greaterThanCondition.first.set(3f);
        assertTrue(greaterThanCondition.test());
    }

    @Test
    public void lessThanConditionTest() {
        LessThanCondition lessThanCondition = new LessThanCondition(1f, 1f);
        assertFalse(lessThanCondition.test());

        lessThanCondition.second.set(3f);
        assertTrue(lessThanCondition.test());
    }

    @Test
    public void compoundConditionTest() {
        CompoundCondition<Boolean> compoundCondition = new CompoundCondition<>(true);
        compoundCondition.and(new BooleanCondition(true))
                .or(new EqualCondition<>(1f, 2f));
        assertTrue(compoundCondition.test());

        compoundCondition.equal(false);
        assertFalse(compoundCondition.test());

        compoundCondition.dataSource.set(false);
        assertTrue(compoundCondition.test());
    }

    @Test
    public void numericCompoundConditionTest() {
        NumericCompoundCondition numericCompoundCondition = new NumericCompoundCondition(3f);
        numericCompoundCondition.greaterThan(2f)
                .lessThan(4f)
                .greaterThan(1.5f);
        assertTrue(numericCompoundCondition.test());

        ICondition.DataSource<Float> dataSource = new ICondition.DataSource<>(4f);

        numericCompoundCondition.greaterThan(dataSource);
        assertFalse(numericCompoundCondition.test());

        dataSource.set(2f);
        assertTrue(numericCompoundCondition.test());
    }
}


















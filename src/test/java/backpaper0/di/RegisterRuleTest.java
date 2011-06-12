package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RegisterRuleTest {

    @Test
    public void testRegister() throws Exception {
        RegisterRule registerRule = new RegisterRule();
        registerRule.addRule(Bean1.class, Scope.SINGLETON);
        registerRule.addRule(Bean2.class, Scope.PROTOTYPE);

        Container container = new Container();
        registerRule.register(container);

        Bean1 component1 = container.get(Bean1.class);
        assertThat(component1, is(notNullValue()));
        Bean1 component2 = container.get(Bean1.class);
        assertThat(component2, is(notNullValue()));
        assertThat(component1, is(sameInstance(component2)));

        Bean2 component3 = container.get(Bean2.class);
        assertThat(component3, is(notNullValue()));
        Bean2 component4 = container.get(Bean2.class);
        assertThat(component4, is(notNullValue()));
        assertThat(component3, is(not(sameInstance(component4))));
    }

    @Test(expected = RuntimeException.class)
    public void testBadDuplicationRule() throws Exception {
        RegisterRule register = new RegisterRule();
        register.addRule(Bean1.class, Scope.SINGLETON);
        register.addRule(Bean1.class, Scope.SINGLETON);
    }

    public static class Bean1 {
    }

    public static class Bean2 {
    }
}

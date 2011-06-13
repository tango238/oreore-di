package backpaper0.di.example;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.Container;
import backpaper0.di.Scope;
import backpaper0.di.annotation.Inject;
import backpaper0.di.config.Configuration;
import backpaper0.di.register.impl.SimpleRegisterRule;

public class Example1 {

    @Test
    public void example() {
        Container container = new Container();
        SimpleRegisterRule rule = new SimpleRegisterRule();
        rule.addRule(Bean2.class, Scope.SINGLETON);
        rule.addRule(Bean1.class, Scope.SINGLETON);
        Configuration config = new Configuration();
        config.setRegisterRule(rule);
        container.init(config);

        Bean1 bean1 = container.get(Bean1.class);
        assertThat(bean1.getBean2(), is(notNullValue()));
    }

    public static class Bean1 {

        private Bean2 bean2;

        @Inject
        public void setBean2(Bean2 bean2) {
            this.bean2 = bean2;
        }

        public Bean2 getBean2() {
            return bean2;
        }
    }

    public static class Bean2 {

    }
}

package backpaper0.di.inject.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.Container;
import backpaper0.di.config.impl.DefaultConfiguration;
import backpaper0.di.inject.Injector;
import backpaper0.di.manager.impl.SingletonComponentManager;
import backpaper0.di.register.impl.SimpleRegisterRule;
import backpaper0.di.testing.InjectBean1;
import backpaper0.di.testing.InjectBean2;
import backpaper0.di.testing.InjectBean3;
import backpaper0.di.testing.InjectBean4;

public class InjectorTest {

    @Test
    public void testInject() throws Exception {
        Container container = new Container();
        DefaultConfiguration config = new DefaultConfiguration();
        config.setRegisterRule(new SimpleRegisterRule());
        container.init(config);
        container.register(InjectBean2.class, new SingletonComponentManager(
            InjectBean2.class));

        Injector injector = new DefaultInjector();

        InjectBean1 injectBean1 = new InjectBean1();
        injector.inject(injectBean1, container);

        assertThat(injectBean1.getInjectBean2(), is(sameInstance(container
            .get(InjectBean2.class))));
    }

    @Test
    public void testInject2() throws Exception {
        Container container = new Container();
        DefaultConfiguration config = new DefaultConfiguration();
        config.setRegisterRule(new SimpleRegisterRule());
        container.init(config);
        container.register(InjectBean4.class, new SingletonComponentManager(
            InjectBean4.class));
        container.register(InjectBean2.class, new SingletonComponentManager(
            InjectBean2.class));

        Injector injector = new DefaultInjector();

        InjectBean3 injectBean3 = new InjectBean3();
        injector.inject(injectBean3, container);

        assertThat(injectBean3.getInjectBean4(), is(sameInstance(container
            .get(InjectBean4.class))));
        assertThat(
            injectBean3.getInjectBean4().getInjectBean2(),
            is(sameInstance(container.get(InjectBean2.class))));
    }
}

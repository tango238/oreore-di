package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.manager.SingletonComponentManager;
import backpaper0.di.testing.InjectBean1;
import backpaper0.di.testing.InjectBean2;
import backpaper0.di.testing.InjectBean3;
import backpaper0.di.testing.InjectBean4;

public class InjectorTest {

    @Test
    public void testInject() throws Exception {
        Container container = new Container();
        container.register(InjectBean2.class, new SingletonComponentManager(
            InjectBean2.class));

        Injector injector = new Injector(container);

        InjectBean1 injectBean1 = new InjectBean1();
        injector.inject(injectBean1);

        assertThat(
            injectBean1.getInjectBean2(),
            is(sameInstance(container.get(InjectBean2.class))));
    }

    @Test
    public void testInject2() throws Exception {
        Container container = new Container();
        container.register(InjectBean4.class, new SingletonComponentManager(
            InjectBean4.class));
        container.register(InjectBean2.class, new SingletonComponentManager(
            InjectBean2.class));

        Injector injector = new Injector(container);

        InjectBean3 injectBean3 = new InjectBean3();
        injector.inject(injectBean3);

        assertThat(
            injectBean3.getInjectBean4(),
            is(sameInstance(container.get(InjectBean4.class))));
        assertThat(
            injectBean3.getInjectBean4().getInjectBean2(),
            is(sameInstance(container.get(InjectBean2.class))));
    }
}

package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.testing.Foo;
import backpaper0.di.testing.InjectBean2;
import backpaper0.di.testing.InjectBean3;
import backpaper0.di.testing.InjectBean4;

public class ContainerTest {

    private Container container = new Container();

    @Test
    public void testGet() throws Exception {
        container.register(Foo.class, createFactory(Foo.class));
        Foo component = container.get(Foo.class);
        assertThat(component, is(notNullValue()));
    }

    @Test
    public void testGet_singleton() throws Exception {
        container.register(Foo.class, createFactory(Foo.class));
        Foo component1 = container.get(Foo.class);
        Foo component2 = container.get(Foo.class);
        assertThat(component1, is(sameInstance(component2)));
    }

    @Test
    public void testRegister() throws Exception {
        try {
            // 登録されていない場合は例外
            container.get(Foo.class);
            fail();
        } catch (RuntimeException expected) {
        }
        container.register(Foo.class, createFactory(Foo.class));
        container.get(Foo.class);
    }

    @Test
    public void testHasComponent() throws Exception {
        assertThat(container.hasComponent(Foo.class), is(false));
        container.register(Foo.class, createFactory(Foo.class));
        assertThat(container.hasComponent(Foo.class), is(true));
    }

    @Test
    public void testInject() throws Exception {
        container.register(InjectBean2.class, createFactory(InjectBean2.class));
        container.register(InjectBean3.class, createFactory(InjectBean3.class));
        container.register(InjectBean4.class, createFactory(InjectBean4.class));
        InjectBean3 component = container.get(InjectBean3.class);
        assertThat(component, is(notNullValue()));
        assertThat(component.getInjectBean4(), is(notNullValue()));
        assertThat(
            component.getInjectBean4().getInjectBean2(),
            is(notNullValue()));
    }

    private static SingletonComponentFactory createFactory(
            Class<?> componentClass) {
        return new SingletonComponentFactory(componentClass);
    }

}

package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.testing.Foo;
import backpaper0.di.testing.InjectBean2;
import backpaper0.di.testing.InjectBean3;
import backpaper0.di.testing.InjectBean4;
import backpaper0.di.testing.PostConstructBean1;
import backpaper0.di.testing.PostConstructBean2;

public class ContainerTest {

    private Container container = new Container();

    @Test
    public void testGet() throws Exception {
        container.register(Foo.class, createManager(Foo.class));
        Foo component = container.get(Foo.class);
        assertThat(component, is(notNullValue()));
    }

    @Test
    public void testGet_singleton() throws Exception {
        container.register(Foo.class, createManager(Foo.class));
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
        container.register(Foo.class, createManager(Foo.class));
        container.get(Foo.class);
    }

    @Test
    public void testHasComponent() throws Exception {
        assertThat(container.hasComponent(Foo.class), is(false));
        container.register(Foo.class, createManager(Foo.class));
        assertThat(container.hasComponent(Foo.class), is(true));
    }

    @Test
    public void testInject() throws Exception {
        container.register(InjectBean2.class, createManager(InjectBean2.class));
        container.register(InjectBean3.class, createManager(InjectBean3.class));
        container.register(InjectBean4.class, createManager(InjectBean4.class));
        InjectBean3 component = container.get(InjectBean3.class);
        assertThat(component, is(notNullValue()));
        assertThat(component.getInjectBean4(), is(notNullValue()));
        assertThat(
            component.getInjectBean4().getInjectBean2(),
            is(notNullValue()));
    }

    @Test
    public void testInit() throws Exception {
        try {
            container.get(Foo.class);
            fail();
        } catch (RuntimeException expected) {
        }

        RegisterRule rule = new RegisterRule();
        rule.addRule(Foo.class, Scope.SINGLETON);
        container.init(rule);

        Foo component1 = container.get(Foo.class);
        assertThat(component1, is(notNullValue()));
        Foo component2 = container.get(Foo.class);
        assertThat(component1, is(sameInstance(component2)));
    }

    @Test
    public void testPostConstruct() throws Exception {
        Container container = new Container();
        RegisterRule rule = new RegisterRule();
        rule.addRule(PostConstructBean1.class, Scope.SINGLETON);
        rule.addRule(PostConstructBean2.class, Scope.PROTOTYPE);
        container.init(rule);
        PostConstructBean1 component1 = container.get(PostConstructBean1.class);
        assertThat(component1.called, is(true));

        PostConstructBean2 component2 = container.get(PostConstructBean2.class);
        assertThat(component2.called, is(true));
    }

    private static SingletonComponentManager createManager(
            Class<?> componentClass) {
        return new SingletonComponentManager(componentClass);
    }

}

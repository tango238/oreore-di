package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.testing.Foo;

public class ContainerTest {

    @Test
    public void testGet() throws Exception {
        Container container = new Container();
        container.init();
        Foo component = container.get(Foo.class);
        assertThat(component, is(notNullValue()));
    }

    @Test
    public void testGet_singleton() throws Exception {
        Container container = new Container();
        container.init();
        Foo component1 = container.get(Foo.class);
        Foo component2 = container.get(Foo.class);
        assertThat(component1, is(sameInstance(component2)));
    }

    @Test
    public void testInit() throws Exception {
        Container container = new Container();
        try {
            // 初期化されていない場合は例外
            container.get(Foo.class);
            fail();
        } catch (RuntimeException expected) {
        }
        container.init();
        container.get(Foo.class);
    }

}

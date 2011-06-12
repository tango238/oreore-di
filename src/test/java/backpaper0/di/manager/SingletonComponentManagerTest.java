package backpaper0.di.manager;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.Container;
import backpaper0.di.Injector;
import backpaper0.di.testing.Foo;

public class SingletonComponentManagerTest {

    @Test
    public void testGet() throws Exception {
        SingletonComponentManager manager = new SingletonComponentManager(
            Foo.class);
        Container container = new Container();
        Injector injector = new Injector(container);
        Foo component1 = (Foo) manager.get(injector);
        Foo component2 = (Foo) manager.get(injector);
        assertThat(component1, is(sameInstance(component2)));
    }
}

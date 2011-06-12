package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.testing.Foo;

public class SingletonComponentManagerTest {

    @Test
    public void testGet() throws Exception {
        SingletonComponentManager manager = new SingletonComponentManager(
            Foo.class);
        Foo component1 = (Foo) manager.get();
        Foo component2 = (Foo) manager.get();
        assertThat(component1, is(sameInstance(component2)));
    }
}

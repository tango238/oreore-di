package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.testing.Foo;

public class PrototypeComponentFactoryTest {

    @Test
    public void testGet() throws Exception {
        PrototypeComponentFactory factory = new PrototypeComponentFactory(
            Foo.class);
        Foo component1 = (Foo) factory.get();
        Foo component2 = (Foo) factory.get();
        assertThat(component1, is(not(sameInstance(component2))));
    }

}

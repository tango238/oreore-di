package backpaper0.di.bean;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class BeanDescFactoryTest {

    @Test
    public void testCreate() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Bean.class);
        assertThat(beanDesc.getBeanClass().getName(), is(Bean.class.getName()));
        List<PropertyDesc> propertyDescs = beanDesc.getPropertyDescs();
        assertThat(propertyDescs.size(), is(3));
        assertThat(propertyDescs.get(0).getName(), is("foo"));
        assertThat(propertyDescs.get(1).getName(), is("bar"));
        assertThat(propertyDescs.get(2).getName(), is("baz"));
    }

    @Test
    public void testCreate_subclass() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Bean2.class);
        assertThat(beanDesc.getBeanClass().getName(), is(Bean2.class.getName()));
        List<PropertyDesc> propertyDescs = beanDesc.getPropertyDescs();
        assertThat(propertyDescs.size(), is(3));
        assertThat(propertyDescs.get(0).getName(), is("foo"));
        assertThat(propertyDescs.get(1).getName(), is("bar"));
        assertThat(propertyDescs.get(2).getName(), is("baz"));
    }

    public static class Bean {

        private boolean bar;

        private int baz;

        private String foo;

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public boolean isBar() {
            return bar;
        }

        public void setBaz(int baz) {
            this.baz = baz;
        }

        public String getFoo() {
            return foo;
        }

    }

    public static class Bean2 extends Bean {

    }
}

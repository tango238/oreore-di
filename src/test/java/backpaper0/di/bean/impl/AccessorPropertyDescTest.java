package backpaper0.di.bean.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import backpaper0.di.annotation.Inject;
import backpaper0.di.bean.PropertyDesc;

public class AccessorPropertyDescTest {

    @Test
    public void test() throws Exception {
        Method getter = Bean.class.getMethod("getFoo");
        Method setter = Bean.class.getMethod("setFoo", String.class);
        PropertyDesc foo = new AccessorPropertyDesc("foo", getter, setter);

        Bean bean = new Bean();
        assertThat(bean.foo, is(nullValue()));
        foo.set(bean, "hello");
        assertThat(bean.foo, is("hello"));
        assertThat(foo.get(bean), is((Object) "hello"));

        assertThat(foo.getName(), is("foo"));
    }

    @Test
    public void testIsInject() throws Exception {
        Class<?> c = Bean2.class;
        PropertyDesc foo = new AccessorPropertyDesc(
            "foo",
            c.getMethod("getFoo"),
            c.getMethod("setFoo", String.class));
        PropertyDesc bar = new AccessorPropertyDesc(
            "bar",
            c.getMethod("getBar"),
            c.getMethod("setBar", String.class));

        assertThat(foo.getAnnotation(Inject.class), is(notNullValue()));
        assertThat(bar.getAnnotation(Inject.class), is(nullValue()));
    }

    @Test
    public void testReadOnly() throws Exception {
        //TODO
        fail();
    }

    @Test
    public void testWriteOnly() throws Exception {
        //TODO
        fail();
    }

    public static class Bean {

        private String foo;

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }

    }

    public static class Bean2 {

        private String foo;

        private String bar;

        @Inject
        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }

        public void setBar(String bar) {
            this.bar = bar;
        }

        public String getBar() {
            return bar;
        }

    }
}

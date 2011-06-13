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
        PropertyDesc readOnly = new AccessorPropertyDesc(
            "readOnly",
            Bean3.class.getMethod("getReadOnly"),
            null);
        PropertyDesc writeOnly = new AccessorPropertyDesc(
            "writeOnly",
            null,
            Bean3.class.getMethod("setWriteOnly", String.class));
        PropertyDesc readAndWrite = new AccessorPropertyDesc(
            "readAndWrite",
            Bean3.class.getMethod("getReadAndWrite"),
            Bean3.class.getMethod("setReadAndWrite", String.class));
        assertThat(readOnly.isReadOnly(), is(true));
        assertThat(writeOnly.isReadOnly(), is(false));
        assertThat(readAndWrite.isReadOnly(), is(false));
    }

    @Test
    public void testWriteOnly() throws Exception {
        PropertyDesc readOnly = new AccessorPropertyDesc(
            "readOnly",
            Bean3.class.getMethod("getReadOnly"),
            null);
        PropertyDesc writeOnly = new AccessorPropertyDesc(
            "writeOnly",
            null,
            Bean3.class.getMethod("setWriteOnly", String.class));
        PropertyDesc readAndWrite = new AccessorPropertyDesc(
            "readAndWrite",
            Bean3.class.getMethod("getReadAndWrite"),
            Bean3.class.getMethod("setReadAndWrite", String.class));
        assertThat(readOnly.isWriteOnly(), is(false));
        assertThat(writeOnly.isWriteOnly(), is(true));
        assertThat(readAndWrite.isWriteOnly(), is(false));
    }

    @Test
    public void testGetter() throws Exception {
        PropertyDesc readOnly = new AccessorPropertyDesc(
            "readOnly",
            Bean3.class.getMethod("getReadOnly"),
            null);
        PropertyDesc writeOnly = new AccessorPropertyDesc(
            "writeOnly",
            null,
            Bean3.class.getMethod("setWriteOnly", String.class));
        PropertyDesc readAndWrite = new AccessorPropertyDesc(
            "readAndWrite",
            Bean3.class.getMethod("getReadAndWrite"),
            Bean3.class.getMethod("setReadAndWrite", String.class));
        Bean3 instance = new Bean3();
        readOnly.get(instance);
        try {
            writeOnly.get(instance);
            fail();
        } catch (UnsupportedOperationException expected) {
        }
        readAndWrite.get(instance);
    }

    @Test
    public void testSetter() throws Exception {
        PropertyDesc readOnly = new AccessorPropertyDesc(
            "readOnly",
            Bean3.class.getMethod("getReadOnly"),
            null);
        PropertyDesc writeOnly = new AccessorPropertyDesc(
            "writeOnly",
            null,
            Bean3.class.getMethod("setWriteOnly", String.class));
        PropertyDesc readAndWrite = new AccessorPropertyDesc(
            "readAndWrite",
            Bean3.class.getMethod("getReadAndWrite"),
            Bean3.class.getMethod("setReadAndWrite", String.class));
        Bean3 instance = new Bean3();
        try {
            readOnly.set(instance, "test");
            fail();
        } catch (UnsupportedOperationException expected) {
        }
        writeOnly.set(instance, "test");
        readAndWrite.set(instance, "test");
    }

    @Test
    public void testToString() throws Exception {
        PropertyDesc readOnly = new AccessorPropertyDesc(
            "readOnly",
            Bean3.class.getMethod("getReadOnly"),
            null);
        assertThat(readOnly.toString(), is("Bean3#readOnly"));
    }

    public static class Bean3 {

        private String readOnly;

        private String writeOnly;

        private String readAndWrite;

        public void setReadAndWrite(String readAndWrite) {
            this.readAndWrite = readAndWrite;
        }

        public String getReadAndWrite() {
            return readAndWrite;
        }

        public String getReadOnly() {
            return readOnly;
        }

        public void setWriteOnly(String writeOnly) {
            this.writeOnly = writeOnly;
        }
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

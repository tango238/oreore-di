package backpaper0.di.bean.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.junit.Test;

import backpaper0.di.bean.BeanDesc;
import backpaper0.di.bean.MethodDesc;
import backpaper0.di.bean.PropertyDesc;

public class BeanDescFactoryTest {

    private DefaultBeanDescProvider provider = new DefaultBeanDescProvider();

    @Test
    public void testCreate() throws Exception {
        BeanDesc beanDesc = provider.createBeanDesc(Bean.class);
        assertThat(beanDesc.getBeanClass().getName(), is(Bean.class.getName()));
        List<PropertyDesc> propertyDescs = beanDesc.getPropertyDescs();
        assertThat(propertyDescs.size(), is(3));
        assertThat(propertyDescs.get(0).getName(), is("foo"));
        assertThat(propertyDescs.get(1).getName(), is("bar"));
        assertThat(propertyDescs.get(2).getName(), is("baz"));
    }

    @Test
    public void testCreate_subclass() throws Exception {
        BeanDesc beanDesc = provider.createBeanDesc(Bean2.class);
        assertThat(beanDesc.getBeanClass().getName(), is(Bean2.class.getName()));
        List<PropertyDesc> propertyDescs = beanDesc.getPropertyDescs();
        assertThat(propertyDescs.size(), is(3));
        assertThat(propertyDescs.get(0).getName(), is("foo"));
        assertThat(propertyDescs.get(1).getName(), is("bar"));
        assertThat(propertyDescs.get(2).getName(), is("baz"));
    }

    @Test
    public void testBeanMethods() throws Exception {
        BeanDesc beanDesc = provider.createBeanDesc(Bean3.class);
        List<MethodDesc> methodDescs = beanDesc.getMethodDescs();
        assertThat(methodDescs.size(), is(1));
        MethodDesc bar = methodDescs.get(0);
        assertThat(bar.getName(), is("bar"));
        assertThat(bar.getAnnotation(Anno1.class), is(notNullValue()));
        Bean3 bean3 = new Bean3();
        assertThat(bean3.called, is(false));
        bar.invoke(bean3);
        assertThat(bean3.called, is(true));
    }

    @Test
    public void testWithPublicFieldPropertyDesc() throws Exception {
        BeanDesc beanDesc = provider.createBeanDesc(Bean4.class);
        List<PropertyDesc> propertyDescs = beanDesc.getPropertyDescs();
        assertThat(propertyDescs.size(), is(2));
        assertThat(propertyDescs.get(0).getName(), is("bar"));
        assertThat(propertyDescs.get(1).getName(), is("foo"));
    }

    @Test
    public void testWithPublicFieldPropertyDesc_inherit() throws Exception {
        BeanDesc beanDesc = provider.createBeanDesc(Bean5.class);
        List<PropertyDesc> propertyDescs = beanDesc.getPropertyDescs();
        assertThat(propertyDescs.size(), is(4));
        assertThat(propertyDescs.get(0).getName(), is("baz"));
        assertThat(propertyDescs.get(1).getName(), is("bar"));
        assertThat(propertyDescs.get(2).getName(), is("qux"));
        assertThat(propertyDescs.get(3).getName(), is("foo"));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public static @interface Anno1 {

    }

    public static class Bean3 {

        public boolean called = false;

        private String foo;

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }

        @Anno1
        public void bar() {
            called = true;
        }

        private int baz() {
            return 0;
        }

        public static void qux(String a, String b) {

        }
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

    public static class Bean4 {

        public String foo;

        public String getBar() {
            return "a";
        }
    }

    public static class Bean5 extends Bean4 {

        public void setBaz(int baz) {
        }

        public boolean qux;
    }
}

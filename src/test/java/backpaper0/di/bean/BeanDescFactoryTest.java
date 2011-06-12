package backpaper0.di.bean;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

import backpaper0.di.Container;
import backpaper0.di.Injector;
import backpaper0.di.testing.IsSetterBean;

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

    @Test
    public void testIsGetterName() throws Exception {
        assertThat(BeanDescFactory.isGetterName("getFoo"), is(true));
        assertThat(BeanDescFactory.isGetterName("getF"), is(true));
        assertThat(BeanDescFactory.isGetterName("getfoo"), is(false));
        assertThat(BeanDescFactory.isGetterName("isFoo"), is(true));
        assertThat(BeanDescFactory.isGetterName("isF"), is(true));
        assertThat(BeanDescFactory.isGetterName("isfoo"), is(false));
    }

    @Test
    public void testIsSetter() throws Exception {
        assertThat(BeanDescFactory.isSetterName("setFoo"), is(true));
        assertThat(BeanDescFactory.isSetterName("setF"), is(true));
        assertThat(BeanDescFactory.isSetterName("setfoo"), is(false));
    }

    @Test
    public void testIsSetter2() throws Exception {
        Injector injector = new Injector(new Container());
        Method setter1 = IsSetterBean.class.getMethod("setAaa", String.class);
        Method setter2 = IsSetterBean.class.getMethod("setB", int.class);
        Method unsetter1 = IsSetterBean.class.getMethod("setccc", String.class);
        Method unsetter2 = IsSetterBean.class.getMethod("setDdd", int.class);
        Method unsetter3 = IsSetterBean.class.getMethod(
            "setEee",
            String.class,
            int.class);
        Method unsetter4 = IsSetterBean.class.getMethod("setFff");
        Method unpublic1 = IsSetterBean.class.getDeclaredMethod(
            "setGgg",
            String.class);
        Method uninstanceSetter1 = IsSetterBean.class.getDeclaredMethod(
            "setHhh",
            String.class);

        assertThat(BeanDescFactory.isSetter(setter1), is(true));
        assertThat(BeanDescFactory.isSetter(setter2), is(true));
        assertThat(BeanDescFactory.isSetter(unsetter1), is(false));
        assertThat(BeanDescFactory.isSetter(unsetter2), is(false));
        assertThat(BeanDescFactory.isSetter(unsetter3), is(false));
        assertThat(BeanDescFactory.isSetter(unsetter4), is(false));
        assertThat(BeanDescFactory.isSetter(unpublic1), is(false));
        assertThat(BeanDescFactory.isSetter(uninstanceSetter1), is(false));
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

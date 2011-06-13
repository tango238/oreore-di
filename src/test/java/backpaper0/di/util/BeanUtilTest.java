package backpaper0.di.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import backpaper0.di.testing.IsSetterBean;

public class BeanUtilTest {

    @Test
    public void testIsGetterName() throws Exception {
        assertThat(BeanUtil.isGetterName("getFoo"), is(true));
        assertThat(BeanUtil.isGetterName("getF"), is(true));
        assertThat(BeanUtil.isGetterName("getfoo"), is(false));
        assertThat(BeanUtil.isGetterName("isFoo"), is(true));
        assertThat(BeanUtil.isGetterName("isF"), is(true));
        assertThat(BeanUtil.isGetterName("isfoo"), is(false));
    }

    @Test
    public void testIsSetter() throws Exception {
        assertThat(BeanUtil.isSetterName("setFoo"), is(true));
        assertThat(BeanUtil.isSetterName("setF"), is(true));
        assertThat(BeanUtil.isSetterName("setfoo"), is(false));
    }

    @Test
    public void testIsSetter2() throws Exception {
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

        assertThat(BeanUtil.isSetter(setter1), is(true));
        assertThat(BeanUtil.isSetter(setter2), is(true));
        assertThat(BeanUtil.isSetter(unsetter1), is(false));
        assertThat(BeanUtil.isSetter(unsetter2), is(false));
        assertThat(BeanUtil.isSetter(unsetter3), is(false));
        assertThat(BeanUtil.isSetter(unsetter4), is(false));
        assertThat(BeanUtil.isSetter(unpublic1), is(false));
        assertThat(BeanUtil.isSetter(uninstanceSetter1), is(false));
    }

}

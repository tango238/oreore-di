package backpaper0.di.bean.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import backpaper0.di.bean.PropertyDesc;

public class PublicFieldPropertyDescTest {

    @Test
    public void testGet() throws Exception {
        Bean bean = new Bean();
        PropertyDesc foobar = new PublicFieldPropertyDesc(
            "foobar",
            Bean.class.getField("foobar"));
        assertThat(foobar.get(bean), is(nullValue()));
        bean.foobar = "a";
        assertThat((String) foobar.get(bean), is("a"));
    }

    @Test
    public void testSet() throws Exception {
        Bean bean = new Bean();
        PropertyDesc foobar = new PublicFieldPropertyDesc(
            "foobar",
            Bean.class.getField("foobar"));
        assertThat(bean.foobar, is(nullValue()));
        foobar.set(bean, "a");
        assertThat(bean.foobar, is("a"));
    }

    public static class Bean {

        public String foobar;
    }
}

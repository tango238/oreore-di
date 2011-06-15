package backpaper0.di.bean;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BeanDescFactoryTest {

    @Test
    public void testGetBeanDesc() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Bean.class);
        assertThat(beanDesc, is(notNullValue()));
    }

    @Test
    public void testCreateBeanDescProvider() throws Exception {
        final String key = "BeanDescProvider";
        final String original = System.getProperty(key);
        final String providerClassName = "backpaper0.di.bean.BeanDescFactoryTest$Foobar";
        System.setProperty(key, providerClassName);
        try {
            BeanDescProvider provider = BeanDescFactory
                .createBeanDescProvider();
            assertThat(provider, is(instanceOf(Foobar.class)));
        } finally {
            if (original != null) {
                System.setProperty(key, original);
            } else {
                System.getProperties().remove(key);
            }
            assertThat(System.getProperty(key), is(nullValue()));
        }
    }

    public static class Bean {

    }

    public static class Foobar implements BeanDescProvider {

        @Override
        public BeanDesc createBeanDesc(Class<?> beanClass) {
            return null;
        }

    }

}

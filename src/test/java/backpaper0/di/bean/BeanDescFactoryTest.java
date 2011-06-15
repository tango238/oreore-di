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

    public static class Bean {

    }
}

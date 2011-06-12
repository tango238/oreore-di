package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import backpaper0.di.testing.InjectBean1;
import backpaper0.di.testing.InjectBean2;
import backpaper0.di.testing.InjectBean3;
import backpaper0.di.testing.InjectBean4;
import backpaper0.di.testing.IsSetterBean;

public class InjectorTest {

    @Test
    public void testIsSetter() throws Exception {
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

        assertThat(injector.isSetter(setter1), is(true));
        assertThat(injector.isSetter(setter2), is(true));
        assertThat(injector.isSetter(unsetter1), is(false));
        assertThat(injector.isSetter(unsetter2), is(false));
        assertThat(injector.isSetter(unsetter3), is(false));
        assertThat(injector.isSetter(unsetter4), is(false));
        assertThat(injector.isSetter(unpublic1), is(false));
        assertThat(injector.isSetter(uninstanceSetter1), is(false));
    }

    @Test
    public void testInject() throws Exception {
        Container container = new Container();
        container.register(InjectBean2.class, new SingletonComponentManager(
            InjectBean2.class));

        Injector injector = new Injector(container);

        InjectBean1 injectBean1 = new InjectBean1();
        injector.inject(injectBean1);

        assertThat(
            injectBean1.getInjectBean2(),
            is(sameInstance(container.get(InjectBean2.class))));
    }

    @Test
    public void testInject2() throws Exception {
        Container container = new Container();
        container.register(InjectBean4.class, new SingletonComponentManager(
            InjectBean4.class));
        container.register(InjectBean2.class, new SingletonComponentManager(
            InjectBean2.class));

        Injector injector = new Injector(container);

        InjectBean3 injectBean3 = new InjectBean3();
        injector.inject(injectBean3);

        assertThat(
            injectBean3.getInjectBean4(),
            is(sameInstance(container.get(InjectBean4.class))));
        assertThat(
            injectBean3.getInjectBean4().getInjectBean2(),
            is(sameInstance(container.get(InjectBean2.class))));
    }
}

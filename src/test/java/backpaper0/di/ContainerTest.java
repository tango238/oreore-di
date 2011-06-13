package backpaper0.di;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import backpaper0.di.manager.SingletonComponentManager;
import backpaper0.di.testing.Foo;
import backpaper0.di.testing.InjectBean2;
import backpaper0.di.testing.InjectBean3;
import backpaper0.di.testing.InjectBean4;
import backpaper0.di.testing.PostConstructBean1;
import backpaper0.di.testing.PostConstructBean2;
import backpaper0.di.testing.PreDestroyBean1;
import backpaper0.di.testing.PreDestroyBean2;

public class ContainerTest {

    private Container container = new Container();

    @Test
    public void testGet() throws Exception {
        container.init(new RegisterRule());
        container.register(Foo.class, createManager(Foo.class));
        Foo component = container.get(Foo.class);
        assertThat(component, is(notNullValue()));
    }

    @Test
    public void testGet_singleton() throws Exception {
        container.init(new RegisterRule());
        container.register(Foo.class, createManager(Foo.class));
        Foo component1 = container.get(Foo.class);
        Foo component2 = container.get(Foo.class);
        assertThat(component1, is(sameInstance(component2)));
    }

    @Test
    public void testRegister() throws Exception {
        container.init(new RegisterRule());
        try {
            // 登録されていない場合は例外
            container.get(Foo.class);
            fail();
        } catch (RuntimeException expected) {
            assertThat(expected.getMessage(), is("コンポーネントがコンテナに登録されていません。"
                    + Foo.class));
        }
        container.register(Foo.class, createManager(Foo.class));
        container.get(Foo.class);
    }

    @Test
    public void testHas() throws Exception {
        container.init(new RegisterRule());
        assertThat(container.has(Foo.class), is(false));
        container.register(Foo.class, createManager(Foo.class));
        assertThat(container.has(Foo.class), is(true));
    }

    @Test
    public void testHas_not_yet_initialized() throws Exception {
        try {
            container.has(Foo.class);
            fail();
        } catch (RuntimeException expected) {
            assertThat(expected.getMessage(), is("コンテナが初期化されていません。"));
        }
        container.init(new RegisterRule());
        container.has(Foo.class);
    }

    @Test
    public void testInject() throws Exception {
        container.init(new RegisterRule());
        container.register(InjectBean2.class, createManager(InjectBean2.class));
        container.register(InjectBean3.class, createManager(InjectBean3.class));
        container.register(InjectBean4.class, createManager(InjectBean4.class));
        InjectBean3 component = container.get(InjectBean3.class);
        assertThat(component, is(notNullValue()));
        assertThat(component.getInjectBean4(), is(notNullValue()));
        assertThat(
            component.getInjectBean4().getInjectBean2(),
            is(notNullValue()));
    }

    @Test
    public void testInit() throws Exception {
        try {
            container.get(Foo.class);
            fail();
        } catch (RuntimeException expected) {
            assertThat(expected.getMessage(), is("コンテナが初期化されていません。"));
        }

        RegisterRule rule = new RegisterRule();
        rule.addRule(Foo.class, Scope.SINGLETON);
        container.init(rule);

        Foo component1 = container.get(Foo.class);
        assertThat(component1, is(notNullValue()));
        Foo component2 = container.get(Foo.class);
        assertThat(component1, is(sameInstance(component2)));
    }

    @Test
    public void testPostConstruct() throws Exception {
        RegisterRule rule = new RegisterRule();
        rule.addRule(PostConstructBean1.class, Scope.SINGLETON);
        rule.addRule(PostConstructBean2.class, Scope.PROTOTYPE);
        container.init(rule);
        PostConstructBean1 component1 = container.get(PostConstructBean1.class);
        assertThat(component1.called, is(true));

        PostConstructBean2 component2 = container.get(PostConstructBean2.class);
        assertThat(component2.called, is(true));
    }

    @Test
    public void testPreDestroy() throws Exception {
        RegisterRule rule = new RegisterRule();
        rule.addRule(PreDestroyBean1.class, Scope.SINGLETON);
        rule.addRule(PreDestroyBean2.class, Scope.PROTOTYPE);
        container.init(rule);

        PreDestroyBean1 component1 = container.get(PreDestroyBean1.class);
        PreDestroyBean2 component2 = container.get(PreDestroyBean2.class);
        PreDestroyBean2 component3 = container.get(PreDestroyBean2.class);

        assertThat(component1.called, is(false));
        assertThat(component2.called, is(false));
        assertThat(component3.called, is(false));

        container.destroy();

        assertThat(component1.called, is(true));
        assertThat(component2.called, is(true));
        assertThat(component3.called, is(true));
    }

    @Test
    public void testMultiThread() throws Exception {
        final int size = 10;
        final CountDownLatch start = new CountDownLatch(1);
        final AtomicInteger counter = new AtomicInteger();
        final RegisterRule rule = new RegisterRule() {

            @Override
            public void register(Container container) {
                counter.incrementAndGet();
            }
        };
        final CountDownLatch end = new CountDownLatch(size);
        Callable<Void> task = new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                start.await();
                container.init(rule);
                end.countDown();
                return null;
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(size);
        try {
            for (int i = 0; i < size; i++) {
                service.submit(task);
            }
            start.countDown();
            end.await();
        } finally {
            service.shutdown();
        }
        assertThat(counter.get(), is(1));
    }

    @Test
    public void testMultiThread_notInit_get() throws Exception {
        final CountDownLatch stopper = new CountDownLatch(1);
        final RegisterRule rule = new RegisterRule() {

            @Override
            public void register(Container container) {
                try {
                    stopper.await();
                } catch (InterruptedException e) {
                }
                super.register(container);
            }
        };
        rule.addRule(Foo.class, Scope.SINGLETON);
        ExecutorService service = Executors.newFixedThreadPool(1);
        try {
            Future<Void> future = service.submit(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    container.init(rule);
                    return null;
                }
            });
            try {
                container.get(Foo.class);
                fail();
            } catch (RuntimeException expected) {
                assertThat(expected.getMessage(), is("コンテナが初期化されていません。"));
            }
            stopper.countDown();
            future.get();
            container.get(Foo.class);
        } finally {
            service.shutdown();
        }
    }

    private static SingletonComponentManager createManager(
            Class<?> componentClass) {
        return new SingletonComponentManager(componentClass);
    }

}

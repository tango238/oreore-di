package backpaper0.di.testing;

import backpaper0.di.annotation.PreDestroy;

public class PreDestroyBean1 {

    public boolean called = false;

    @PreDestroy
    public void method1() {
        called = true;
    }
}

package backpaper0.di.testing;

import javax.annotation.PreDestroy;

public class PreDestroyBean1 {

    public boolean called = false;

    @PreDestroy
    public void method1() {
        called = true;
    }
}

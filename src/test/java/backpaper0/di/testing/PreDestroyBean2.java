package backpaper0.di.testing;

import javax.annotation.PreDestroy;

public class PreDestroyBean2 {

    public boolean called = false;

    @PreDestroy
    public void method1() {
        called = true;
    }
}

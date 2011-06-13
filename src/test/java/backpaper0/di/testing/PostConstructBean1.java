package backpaper0.di.testing;

import javax.annotation.PostConstruct;

public class PostConstructBean1 {

    public boolean called = false;

    @PostConstruct
    public void method1() {
        called = true;
    }
}

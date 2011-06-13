package backpaper0.di.testing;

import javax.annotation.PostConstruct;

public class PostConstructBean2 {

    public boolean called = false;

    @PostConstruct
    public void method1() {
        called = true;
    }
}

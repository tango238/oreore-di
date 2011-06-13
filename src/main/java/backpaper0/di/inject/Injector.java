package backpaper0.di.inject;

import backpaper0.di.Container;

public interface Injector {

    <T> void inject(T component, Container container);

}

package backpaper0.di.manager;

import backpaper0.di.Container;
import backpaper0.di.inject.Injector;

public interface ComponentManager {

    Object get(Injector injector, Container container);

    void destroy();

}

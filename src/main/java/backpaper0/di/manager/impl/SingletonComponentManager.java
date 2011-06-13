package backpaper0.di.manager.impl;

import backpaper0.di.Container;
import backpaper0.di.inject.Injector;

public class SingletonComponentManager extends AbstractComponentManager {

    public SingletonComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get(Injector injector, Container container) {
        if (components.isEmpty()) {
            createComponent(injector, container);
        }
        return components.iterator().next();
    }

}

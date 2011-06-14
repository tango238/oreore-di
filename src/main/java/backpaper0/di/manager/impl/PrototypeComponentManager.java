package backpaper0.di.manager.impl;

import backpaper0.di.Container;
import backpaper0.di.inject.Injector;

public class PrototypeComponentManager extends AbstractComponentManager {

    public PrototypeComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get(Injector injector, Container container) {
        final Object component = createComponent(injector, container);
        return component;
    }
}

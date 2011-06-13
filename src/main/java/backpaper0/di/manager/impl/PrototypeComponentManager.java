package backpaper0.di.manager.impl;

import backpaper0.di.inject.Injector;

public class PrototypeComponentManager extends AbstractComponentManager {

    public PrototypeComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get(Injector injector) {
        Object component = createComponent(injector);
        return component;
    }
}

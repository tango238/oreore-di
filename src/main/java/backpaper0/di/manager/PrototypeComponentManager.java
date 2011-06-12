package backpaper0.di.manager;

import backpaper0.di.Injector;

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

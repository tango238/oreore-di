package backpaper0.di.manager;

import backpaper0.di.Injector;

public class SingletonComponentManager extends AbstractComponentManager {

    public SingletonComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get(Injector injector) {
        if (components.isEmpty()) {
            createComponent(injector);
        }
        return components.iterator().next();
    }

}

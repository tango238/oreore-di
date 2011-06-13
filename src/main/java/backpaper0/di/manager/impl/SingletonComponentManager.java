package backpaper0.di.manager.impl;

import backpaper0.di.Container;
import backpaper0.di.bean.MethodDesc;
import backpaper0.di.inject.Injector;
import backpaper0.di.lifecycle.BuiltInLifecycles;
import backpaper0.di.lifecycle.LifecycleEvent;
import backpaper0.di.lifecycle.LifecycleListener;

public class SingletonComponentManager extends AbstractComponentManager {

    public SingletonComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get(Injector injector, Container container) {
        if (components.isEmpty()) {
            final Object singleton = createComponent(injector, container);
            container.addLifecycleListener(
                BuiltInLifecycles.CONTAINER_PRE_DESTROY,
                new LifecycleListener() {

                    @Override
                    public void apply(LifecycleEvent event) {
                        for (MethodDesc preDestroyMethod : preDestroyMethods) {
                            preDestroyMethod.invoke(singleton);
                        }
                    }
                });
        }
        return components.iterator().next();
    }

}

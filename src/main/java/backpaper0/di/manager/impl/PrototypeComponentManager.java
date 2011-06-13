package backpaper0.di.manager.impl;

import backpaper0.di.Container;
import backpaper0.di.Container.LifecycleEvent;
import backpaper0.di.Container.LifecycleListener;
import backpaper0.di.bean.MethodDesc;
import backpaper0.di.inject.Injector;

public class PrototypeComponentManager extends AbstractComponentManager {

    public PrototypeComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get(Injector injector, Container container) {
        final Object component = createComponent(injector, container);
        container.addLifecycleListener(
            Container.CONTAINER_DESTROY,
            new LifecycleListener() {

                @Override
                public void apply(LifecycleEvent event) {
                    for (MethodDesc preDestroyMethod : preDestroyMethods) {
                        preDestroyMethod.invoke(component);
                    }
                }
            });
        return component;
    }
}

package backpaper0.di.manager.impl;

import java.util.Collection;

import backpaper0.di.bean.MethodDesc;
import backpaper0.di.lifecycle.LifecycleEvent;
import backpaper0.di.lifecycle.LifecycleListener;

public class ComponentDestroyLifecycleListener implements LifecycleListener {

    private Object component;

    private Collection<MethodDesc> preDestroyMethods;

    public ComponentDestroyLifecycleListener(Object component,
            Collection<MethodDesc> preDestroyMethods) {
        this.component = component;
        this.preDestroyMethods = preDestroyMethods;
    }

    @Override
    public void apply(LifecycleEvent event) {
        for (MethodDesc preDestroyMethod : preDestroyMethods) {
            preDestroyMethod.invoke(component);
        }
    }

}

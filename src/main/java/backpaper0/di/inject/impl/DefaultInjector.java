package backpaper0.di.inject.impl;

import java.util.Collection;

import backpaper0.di.Container;
import backpaper0.di.bean.PropertyDesc;
import backpaper0.di.inject.Injector;

public class DefaultInjector implements Injector {

    @Override
    public <T> void inject(T targetComponent,
            Collection<PropertyDesc> targetPropertyDescs, Container container) {
        for (PropertyDesc propertyDesc : targetPropertyDescs) {
            Class<?> propertyClass = propertyDesc.getPropertyClass();
            Object dependency = container.get(propertyClass);
            propertyDesc.set(targetComponent, dependency);
        }
    }

}

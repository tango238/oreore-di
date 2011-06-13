package backpaper0.di.inject.impl;

import backpaper0.di.Container;
import backpaper0.di.annotation.Inject;
import backpaper0.di.bean.BeanDesc;
import backpaper0.di.bean.BeanDescFactory;
import backpaper0.di.bean.PropertyDesc;
import backpaper0.di.inject.Injector;

public class DefaultInjector implements Injector {

    private Container container;

    public DefaultInjector(Container container) {
        this.container = container;
    }

    @Override
    public <T> void inject(T component) {
        Class<T> componentClass = (Class<T>) component.getClass();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(componentClass);
        for (PropertyDesc propertyDesc : beanDesc.getPropertyDescs()) {
            if (!propertyDesc.isReadOnly()
                    && propertyDesc.getAnnotation(Inject.class) != null) {
                Class<?> propertyClass = propertyDesc.getPropertyClass();
                Object dependency = container.get(propertyClass);
                propertyDesc.set(component, dependency);
            }
        }
    }

}

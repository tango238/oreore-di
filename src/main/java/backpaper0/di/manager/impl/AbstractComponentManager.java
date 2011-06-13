package backpaper0.di.manager.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import backpaper0.di.Container;
import backpaper0.di.bean.BeanDesc;
import backpaper0.di.bean.BeanDescFactory;
import backpaper0.di.bean.MethodDesc;
import backpaper0.di.inject.Injector;
import backpaper0.di.manager.ComponentManager;
import backpaper0.di.util.ClassUtil;

public abstract class AbstractComponentManager implements ComponentManager {

    protected Class<?> componentClass;

    protected Collection<MethodDesc> postConstructMethods = new ArrayList<MethodDesc>();

    protected Collection<MethodDesc> preDestroyMethods = new ArrayList<MethodDesc>();

    protected Collection<Object> components = new ArrayList<Object>();

    public AbstractComponentManager(Class<?> componentClass) {
        this.componentClass = componentClass;
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(componentClass);
        for (MethodDesc beanMethod : beanDesc.getMethodDescs()) {
            PostConstruct postConstruct = beanMethod
                .getAnnotation(PostConstruct.class);
            if (postConstruct != null) {
                postConstructMethods.add(beanMethod);
            }
            PreDestroy preDestroy = beanMethod.getAnnotation(PreDestroy.class);
            if (preDestroy != null) {
                preDestroyMethods.add(beanMethod);
            }
        }
    }

    protected Object createComponent(Injector injector, Container container) {
        Object component = ClassUtil.newInstance(componentClass);
        for (MethodDesc postConstructMethod : postConstructMethods) {
            postConstructMethod.invoke(component);
        }
        injector.inject(component, container);
        components.add(component);
        return component;
    }

}

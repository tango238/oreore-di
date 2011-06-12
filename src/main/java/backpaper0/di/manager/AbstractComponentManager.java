package backpaper0.di.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import backpaper0.di.ComponentManager;
import backpaper0.di.annotation.PostConstruct;
import backpaper0.di.util.ClassUtil;
import backpaper0.di.util.MethodUtil;

public abstract class AbstractComponentManager implements ComponentManager {

    protected Class<?> componentClass;

    protected Collection<Method> postConstructMethods = new ArrayList<Method>();

    public AbstractComponentManager(Class<?> componentClass) {
        this.componentClass = componentClass;
        for (Method method : componentClass.getMethods()) {
            PostConstruct postConstruct = method
                .getAnnotation(PostConstruct.class);
            if (postConstruct != null) {
                postConstructMethods.add(method);
            }
        }
    }

    protected Object createComponent() {
        Object component = ClassUtil.newInstance(componentClass);
        for (Method postConstructMethod : postConstructMethods) {
            MethodUtil.invoke(postConstructMethod, component);
        }
        return component;
    }
}
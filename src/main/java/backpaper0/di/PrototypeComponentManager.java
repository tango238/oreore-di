package backpaper0.di;

import java.lang.reflect.Method;

import backpaper0.di.annotation.PostConstruct;
import backpaper0.di.util.ClassUtil;
import backpaper0.di.util.MethodUtil;

public class PrototypeComponentManager implements ComponentManager {

    private Class<?> componentClass;

    public PrototypeComponentManager(Class<?> componentClass) {
        this.componentClass = componentClass;
    }

    @Override
    public Object get() {
        Object component = ClassUtil.newInstance(componentClass);
        for (Method method : componentClass.getMethods()) {
            PostConstruct postConstruct = method
                .getAnnotation(PostConstruct.class);
            if (postConstruct != null) {
                MethodUtil.invoke(method, component);
            }
        }
        return component;
    }

}

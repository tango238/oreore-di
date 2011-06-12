package backpaper0.di;

import java.lang.reflect.Method;

import backpaper0.di.annotation.PostConstruct;
import backpaper0.di.util.ClassUtil;
import backpaper0.di.util.MethodUtil;

public class SingletonComponentManager implements ComponentManager {

    private Object singleton;

    public SingletonComponentManager(Class<?> componentClass) {
        singleton = ClassUtil.newInstance(componentClass);
        for (Method method : componentClass.getMethods()) {
            PostConstruct postConstruct = method
                .getAnnotation(PostConstruct.class);
            if (postConstruct != null) {
                MethodUtil.invoke(method, singleton);
            }
        }
    }

    @Override
    public Object get() {
        return singleton;
    }

}

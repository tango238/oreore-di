package backpaper0.di;

import backpaper0.di.util.ClassUtil;

public class SingletonComponentManager implements ComponentManager {

    private Object singleton;

    public SingletonComponentManager(Class<?> componentClass) {
        singleton = ClassUtil.newInstance(componentClass);
    }

    @Override
    public Object get() {
        return singleton;
    }

}

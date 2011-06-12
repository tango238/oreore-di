package backpaper0.di;

import backpaper0.di.util.ClassUtil;

public class PrototypeComponentManager implements ComponentManager {

    private Class<?> componentClass;

    public PrototypeComponentManager(Class<?> componentClass) {
        this.componentClass = componentClass;
    }

    @Override
    public Object get() {
        return ClassUtil.newInstance(componentClass);
    }

}

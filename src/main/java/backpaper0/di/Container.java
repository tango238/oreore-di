package backpaper0.di;

import java.util.HashMap;
import java.util.Map;

public class Container {

    private boolean initialized = false;

    private Map<Class<Object>, Object> singletons = new HashMap<Class<Object>, Object>();

    public <T> T get(Class<T> componentClass) {
        if (!initialized) {
            throw new RuntimeException("コンテナが初期化されていません。");
        }
        if (!singletons.containsKey(componentClass)) {
            try {
                Object component = componentClass.newInstance();
                singletons.put((Class<Object>) componentClass, component);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        Object singleton = singletons.get(componentClass);
        return (T) singleton;
    }

    public void init() {
        initialized = true;
    }

}

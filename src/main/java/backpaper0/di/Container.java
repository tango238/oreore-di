package backpaper0.di;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Container {

    private static Logger logger = Logger.getLogger(Container.class.getName());

    private ConcurrentMap<Class<?>, ComponentFactory> factories = new ConcurrentHashMap<Class<?>, ComponentFactory>();

    private Injector injector;

    public Container() {
        injector = new Injector(this);
    }

    public <T> T get(Class<T> componentClass) {
        if (!factories.containsKey(componentClass)) {
            throw new RuntimeException("コンポーネントがコンテナに登録されていません。"
                    + componentClass);
        }
        ComponentFactory factory = factories.get(componentClass);
        T component = (T) factory.get();
        injector.inject(component);
        return component;
    }

    public void register(Class<?> componentClass, ComponentFactory factory) {
        factories.put(componentClass, factory);
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("コンポーネントを登録しました。" + componentClass);
        }
    }

    public <T> boolean hasComponent(Class<T> componentClass) {
        return factories.containsKey(componentClass);
    }

}

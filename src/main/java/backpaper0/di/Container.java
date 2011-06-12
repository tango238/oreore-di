package backpaper0.di;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Container {

    private static Logger logger = Logger.getLogger(Container.class.getName());

    private ConcurrentMap<Class<?>, ComponentManager> managers = new ConcurrentHashMap<Class<?>, ComponentManager>();

    private Injector injector;

    public Container() {
        injector = new Injector(this);
    }

    public <T> T get(Class<T> componentClass) {
        if (!managers.containsKey(componentClass)) {
            throw new RuntimeException("コンポーネントがコンテナに登録されていません。"
                    + componentClass);
        }
        ComponentManager factory = managers.get(componentClass);
        T component = (T) factory.get();
        injector.inject(component);
        return component;
    }

    public void register(Class<?> componentClass, ComponentManager manager) {
        managers.put(componentClass, manager);
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("コンポーネントを登録しました。" + componentClass);
        }
    }

    public <T> boolean hasComponent(Class<T> componentClass) {
        return managers.containsKey(componentClass);
    }

    public void init(RegisterRule rule) {
        rule.register(this);
    }

    public void destroy() {
        for (ComponentManager manager : managers.values()) {
            manager.destroy();
        }
        managers.clear();
    }
}

package backpaper0.di;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import backpaper0.di.config.Configuration;
import backpaper0.di.inject.Injector;
import backpaper0.di.manager.ComponentManager;

public class Container {

    private static Logger logger = Logger.getLogger(Container.class.getName());

    private ConcurrentMap<Class<?>, ComponentManager> managers = new ConcurrentHashMap<Class<?>, ComponentManager>();

    private Injector injector;

    private boolean initialized = false;

    public <T> T get(Class<T> componentClass) {
        if (!initialized) {
            throw new RuntimeException("コンテナが初期化されていません。");
        }
        if (!managers.containsKey(componentClass)) {
            throw new RuntimeException("コンポーネントがコンテナに登録されていません。"
                    + componentClass);
        }
        ComponentManager manager = managers.get(componentClass);
        T component = (T) manager.get(injector, this);
        return component;
    }

    public void register(Class<?> componentClass, ComponentManager manager) {
        managers.put(componentClass, manager);
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("コンポーネントを登録しました。" + componentClass);
        }
    }

    public <T> boolean hasComponent(Class<T> componentClass) {
        if (!initialized) {
            throw new RuntimeException("コンテナが初期化されていません。");
        }
        return managers.containsKey(componentClass);
    }

    public synchronized void init(Configuration config) {
        if (!initialized) {
            injector = config.createInjector();
            config.getRegisterRule().register(this);
            initialized = true;
        }
    }

    public void destroy() {
        for (ComponentManager manager : managers.values()) {
            manager.destroy();
        }
        managers.clear();
    }
}

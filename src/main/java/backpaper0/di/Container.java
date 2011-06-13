package backpaper0.di;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import backpaper0.di.config.Configuration;
import backpaper0.di.inject.Injector;
import backpaper0.di.lifecycle.BuiltInLifecycles;
import backpaper0.di.lifecycle.Lifecycle;
import backpaper0.di.lifecycle.LifecycleListener;
import backpaper0.di.lifecycle.LifecycleManager;
import backpaper0.di.manager.ComponentManager;
import backpaper0.di.register.RegisterRule;

/**
 * コンテナです。
 * 
 * <p>インスタンス生成後に{@link #init(RegisterRule) 初期化}してください。
 * 初期化せずに{@link #get(Class) コンポーネントを取得}しようとすると{@link IllegalStateException}が発生します。
 * 
 * @author backpaper0
 *
 */
public class Container {

    private static Logger logger = Logger.getLogger(Container.class.getName());

    private ConcurrentMap<Class<?>, ComponentManager> managers = new ConcurrentHashMap<Class<?>, ComponentManager>();

    private Injector injector;

    private LifecycleManager lifecycleManager = new LifecycleManager();

    private volatile boolean initialized = false;

    /**
     * コンポーネントを返します。
     * 
     * @param <T>
     * @param componentClass コンポーネントのキー
     * @return コンポーネント
     */
    public <T> T get(Class<T> componentClass) {
        if (!initialized) {
            throw new IllegalStateException("コンテナが初期化されていません。");
        }
        if (!managers.containsKey(componentClass)) {
            throw new IllegalArgumentException("コンポーネントがコンテナに登録されていません。"
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

    /**
     * コンテナ内にコンポーネントが存在するか確認して返します。
     * 
     * @param <T>
     * @param componentClass コンポーネントのキー
     * @return {@literal true}ならコンポーネントが存在する
     */
    public <T> boolean has(Class<T> componentClass) {
        if (!initialized) {
            throw new IllegalStateException("コンテナが初期化されていません。");
        }
        return managers.containsKey(componentClass);
    }

    /**
     * コンテナを初期化します。
     * 
     * @param config コンテナの設定
     */
    public synchronized void init(Configuration config) {
        if (!initialized) {
            injector = config.createInjector();
            config.getRegisterRule().register(this);
            initialized = true;
            lifecycleManager.fireLifecycleEvent(
                BuiltInLifecycles.CONTAINER_POST_CONSTRUCT,
                this);
        }
    }

    /**
     * コンテナを破棄します。
     * 
     */
    public void destroy() {
        lifecycleManager.fireLifecycleEvent(
            BuiltInLifecycles.CONTAINER_PRE_DESTROY,
            this);
        managers.clear();
    }

    public void addLifecycleListener(Lifecycle lifecycle,
            LifecycleListener listener) {
        lifecycleManager.addLifecycleListener(lifecycle, listener);
    }
}

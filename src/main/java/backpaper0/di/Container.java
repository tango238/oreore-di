package backpaper0.di;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * コンテナです。
 * 
 * <p>インスタンス生成後に{@link #init(RegisterRule) 初期化}してください。
 * 初期化せずに{@link #get(Class) コンポーネントを取得}しようとすると例外が発生します。
 * 
 * @author backpaper0
 *
 */
public class Container {

    private static Logger logger = Logger.getLogger(Container.class.getName());

    private ConcurrentMap<Class<?>, ComponentManager> managers = new ConcurrentHashMap<Class<?>, ComponentManager>();

    private Injector injector;

    private volatile boolean initialized = false;

    public Container() {
        injector = new Injector(this);
    }

    /**
     * コンポーネントを返します。
     * 
     * @param <T>
     * @param componentClass コンポーネントのキー
     * @return コンポーネント
     */
    public <T> T get(Class<T> componentClass) {
        if (!initialized) {
            throw new RuntimeException("コンテナが初期化されていません。");
        }
        if (!managers.containsKey(componentClass)) {
            throw new RuntimeException("コンポーネントがコンテナに登録されていません。"
                    + componentClass);
        }
        ComponentManager manager = managers.get(componentClass);
        T component = (T) manager.get(injector);
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
            throw new RuntimeException("コンテナが初期化されていません。");
        }
        return managers.containsKey(componentClass);
    }

    /**
     * コンテナを初期化します。
     * 
     * @param rule コンポーネント登録のルール
     */
    public synchronized void init(RegisterRule rule) {
        if (!initialized) {
            rule.register(this);
            initialized = true;
        }
    }

    /**
     * コンテナを破棄します。
     * 
     */
    public void destroy() {
        for (ComponentManager manager : managers.values()) {
            manager.destroy();
        }
        managers.clear();
    }
}

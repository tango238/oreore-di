package backpaper0.di.manager;

import backpaper0.di.Container;
import backpaper0.di.inject.Injector;

/**
 * コンポーネントの生成や依存注入を管理するインターフェースです。
 * 
 * @author backpaper0
 *
 */
public interface ComponentManager {

    /**
     * コンポーネントを返します。
     * 
     * @param injector
     * @param container コンテナ
     * @return この{@link ComponentManager}が管理するコンポーネント
     */
    Object get(Injector injector, Container container);

}

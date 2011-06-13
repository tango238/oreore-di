package backpaper0.di.inject;

import backpaper0.di.Container;

/**
 * コンポーネントの注入を行うインターフェースです。
 * 
 * @author backpaper0
 *
 */
public interface Injector {

    /**
     * コンポーネントの注入を行います。
     * 
     * <p>注入対象となるプロパティは実装依存の方式で求められます。
     * 注入されるコンポーネントはコンテナから取得されます。
     * 
     * @param <T>
     * @param targetComponent 注入の対象となるコンポーネント
     * @param container コンテナ
     */
    <T> void inject(T targetComponent, Container container);

}

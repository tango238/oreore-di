package backpaper0.di.inject;

import java.util.Collection;

import backpaper0.di.Container;
import backpaper0.di.bean.PropertyDesc;

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
     * @param targetPropertyDescs 注入の対象となるプロパティ
     * @param container コンテナ
     */
    <T> void inject(T targetComponent,
            Collection<PropertyDesc> targetPropertyDescs, Container container);

}

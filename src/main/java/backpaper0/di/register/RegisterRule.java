package backpaper0.di.register;

import backpaper0.di.Container;

/**
 * コンテナへコンポーネントを登録する際に適用されるルールです。
 * 
 * @author backpaper0
 *
 */
public interface RegisterRule {

    /**
     * ルールに則りコンテナへコンポーネントを登録します。
     * 
     * <p>登録のルールは実装依存で定められます。
     * 
     * @param container コンテナ
     */
    void register(Container container);
}

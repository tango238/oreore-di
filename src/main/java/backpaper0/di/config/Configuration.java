package backpaper0.di.config;

import backpaper0.di.inject.Injector;
import backpaper0.di.register.RegisterRule;

/**
 * コンテナの設定です。
 * 
 * @author backpaper0
 *
 */
public interface Configuration {

    /**
     * {@link Injector}のインスタンスを生成します。
     * 
     * @return {@link Injector}のインスタンス
     */
    Injector createInjector();

    /**
     * コンポーネント登録ルールを返します。
     * 
     * @return コンポーネント登録ルール
     */
    RegisterRule getRegisterRule();

}

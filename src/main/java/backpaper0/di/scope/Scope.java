package backpaper0.di.scope;

import backpaper0.di.manager.ComponentManager;

//TODO 参照しているのはSimpleRegisterRuleのみ。このインターフェースは必要なのだろうか？
public interface Scope {

    ComponentManager createComponentManager(Class<?> componentClass);

}

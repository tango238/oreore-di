package backpaper0.di.register;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import backpaper0.di.Container;
import backpaper0.di.Scope;
import backpaper0.di.manager.ComponentManager;

public class RegisterRule {

    private Map<Class<?>, Scope> rules = new HashMap<Class<?>, Scope>();

    public void addRule(Class<?> componentClass, Scope scope) {
        if (rules.containsKey(componentClass)) {
            throw new RuntimeException("コンポーネント定義は既に登録されています。" + componentClass);
        }
        rules.put(componentClass, scope);
    }

    public void register(Container container) {
        for (Entry<Class<?>, Scope> rule : rules.entrySet()) {
            Class<?> componentClass = rule.getKey();
            Scope scope = rule.getValue();
            ComponentManager manager = scope
                .createComponentManager(componentClass);
            container.register(componentClass, manager);
        }
    }
}

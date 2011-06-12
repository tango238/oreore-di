package backpaper0.di;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
            if (scope.equals(Scope.SINGLETON)) {
                container.register(
                    componentClass,
                    new SingletonComponentFactory(componentClass));
            } else if (scope.equals(Scope.PROTOTYPE)) {
                container.register(
                    componentClass,
                    new PrototypeComponentFactory(componentClass));
            } else {
                throw new RuntimeException(
                    "スコープは singleton か prototype を指定してください。");
            }
        }
    }
}

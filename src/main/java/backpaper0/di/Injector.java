package backpaper0.di;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Injector {

    private Container container;

    public Injector(Container container) {
        this.container = container;
    }

    public <T> void inject(T component) {
        Class<T> componentClass = (Class<T>) component.getClass();
        Method[] methods = componentClass.getMethods();
        for (Method method : methods) {
            Inject inject = method.getAnnotation(Inject.class);
            if (inject != null && isSetter(method)) {
                Class<?> injectType = method.getParameterTypes()[0];
                Object injected = container.get(injectType);
                MethodUtil.invoke(method, component, injected);
            }
        }
    }

    public boolean isSetter(Method setter) {
        final int modifier = setter.getModifiers();
        if (!Modifier.isPublic(modifier)) {
            return false;
        }
        if (Modifier.isStatic(modifier)) {
            return false;
        }
        final String name = setter.getName();
        if (!name.startsWith("set")) {
            return false;
        }
        if (!Character.isUpperCase(name.charAt(3))) {
            return false;
        }
        if (!setter.getReturnType().equals(Void.TYPE)) {
            return false;
        }
        if (setter.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }
}

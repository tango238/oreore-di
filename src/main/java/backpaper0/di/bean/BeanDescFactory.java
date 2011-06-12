package backpaper0.di.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeanDescFactory {

    private static class AccessorPropertyDescBuilder {

        String name;

        Method getter;

        Method setter;

        AccessorPropertyDesc build() {
            return new AccessorPropertyDesc(name, getter, setter);
        }
    }

    private static final Collection<String> GETTER_PREFIXS = Arrays.asList(
        "get",
        "is");

    private static final String SETTER_PREFIX = "set";

    public static BeanDesc getBeanDesc(Class<?> clazz) {
        Map<String, AccessorPropertyDescBuilder> builders = new LinkedHashMap<String, BeanDescFactory.AccessorPropertyDescBuilder>();
        Class<?> c = clazz;
        do {
            for (Method method : c.getDeclaredMethods()) {
                if (isGetter(method)) {
                    getBuilder(builders, method).getter = method;
                }
                if (isSetter(method)) {
                    getBuilder(builders, method).setter = method;
                }
            }
            c = c.getSuperclass();
        } while (c != null && !c.equals(Object.class));
        List<PropertyDesc> propertyDescs = new ArrayList<PropertyDesc>(
            builders.size());
        for (AccessorPropertyDescBuilder builder : builders.values()) {
            PropertyDesc propertyDesc = builder.build();
            propertyDescs.add(propertyDesc);
        }
        BeanDesc beanDesc = new DefaultBeanDesc(clazz, propertyDescs);
        return beanDesc;
    }

    private static AccessorPropertyDescBuilder getBuilder(
            Map<String, AccessorPropertyDescBuilder> builders, Method method) {
        String name = getPropertyName(method);
        if (!builders.containsKey(name)) {
            AccessorPropertyDescBuilder builder = new AccessorPropertyDescBuilder();
            builder.name = name;
            builders.put(name, builder);
        }
        return builders.get(name);
    }

    private static String getPropertyName(Method method) {
        String name = method.getName();
        String base;
        if (name.startsWith("is")) {
            base = name.substring("is".length());
        } else {
            base = name.substring(3);
        }
        return base.substring(0, 1).toLowerCase() + base.substring(1);
    }

    protected static boolean isGetter(Method method) {
        final int modifier = method.getModifiers();
        if (!Modifier.isPublic(modifier)) {
            return false;
        }
        if (Modifier.isStatic(modifier)) {
            return false;
        }
        if (!isGetterName(method.getName())) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (method.getReturnType().equals(Void.TYPE)) {
            return false;
        }
        return true;
    }

    protected static boolean isGetterName(String name) {
        for (String prefix : GETTER_PREFIXS) {
            if (isAccessorName(name, prefix)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isSetter(Method method) {
        final int modifier = method.getModifiers();
        if (!Modifier.isPublic(modifier)) {
            return false;
        }
        if (Modifier.isStatic(modifier)) {
            return false;
        }
        if (!isSetterName(method.getName())) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        if (!method.getReturnType().equals(Void.TYPE)) {
            return false;
        }
        return true;
    }

    protected static boolean isSetterName(String name) {
        return isAccessorName(name, SETTER_PREFIX);
    }

    private static boolean isAccessorName(String name, String prefix) {
        return name.startsWith(prefix)
                && Character.isUpperCase(name.charAt(prefix.length()));
    }
}

package backpaper0.di.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

public class BeanUtil {

    private static final Collection<String> GETTER_PREFIXS = Arrays.asList(
        "get",
        "is");

    private static final String SETTER_PREFIX = "set";

    public static boolean isGetter(Method method) {
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

    public static boolean isGetterName(String name) {
        for (String prefix : GETTER_PREFIXS) {
            if (isAccessorName(name, prefix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSetter(Method method) {
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

    public static boolean isSetterName(String name) {
        return isAccessorName(name, SETTER_PREFIX);
    }

    private static boolean isAccessorName(String name, String prefix) {
        return name.startsWith(prefix)
                && Character.isUpperCase(name.charAt(prefix.length()));
    }

}

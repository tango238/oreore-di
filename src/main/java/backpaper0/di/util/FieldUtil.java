package backpaper0.di.util;

import java.lang.reflect.Field;

public class FieldUtil {

    public static Object get(Field field, Object instance) {
        try {
            return field.get(instance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void set(Field field, Object instance, Object value) {
        try {
            field.set(instance, value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

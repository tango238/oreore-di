package backpaper0.di.bean;

import java.lang.annotation.Annotation;

public interface PropertyDesc {

    String getName();

    Object get(Object instance);

    void set(Object instance, Object value);

    boolean isReadOnly();

    boolean isWriteOnly();

    <T extends Annotation> T getAnnotation(Class<T> annotationClass);

    Class<?> getPropertyClass();

}

package backpaper0.di.bean;

import java.lang.annotation.Annotation;
import java.util.List;

public interface BeanMethod {

    Object invoke(Object instance, Object... arguments);

    <T extends Annotation> T getAnnotation(Class<T> annotationClass);

    String getName();

    Class<?> getReturnType();

    List<Class<?>> getArgumentsTypes();
}

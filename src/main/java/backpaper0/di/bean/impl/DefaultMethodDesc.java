package backpaper0.di.bean.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import backpaper0.di.bean.MethodDesc;
import backpaper0.di.util.MethodUtil;

public class DefaultMethodDesc implements MethodDesc {

    private Method method;

    public DefaultMethodDesc(Method method) {
        this.method = method;
    }

    @Override
    public Object invoke(Object instance, Object... arguments) {
        return MethodUtil.invoke(method, instance, arguments);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return method.getAnnotation(annotationClass);
    }

    @Override
    public String getName() {
        return method.getName();
    }

    @Override
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    @Override
    public List<Class<?>> getArgumentsTypes() {
        return Arrays.asList(method.getParameterTypes());
    }

}

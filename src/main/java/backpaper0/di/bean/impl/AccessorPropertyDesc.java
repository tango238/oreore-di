package backpaper0.di.bean.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import backpaper0.di.bean.PropertyDesc;
import backpaper0.di.util.MethodUtil;

public class AccessorPropertyDesc implements PropertyDesc {

    private String name;

    private Method getter;

    private Method setter;

    private boolean readOnly;

    private boolean writeOnly;

    private Class<?> propertyClass;

    public AccessorPropertyDesc(String name, Method getter, Method setter) {
        this.name = name;
        this.getter = getter;
        this.setter = setter;
        this.readOnly = (setter == null);
        this.writeOnly = (getter == null);
        this.propertyClass = (getter != null ? getter.getReturnType() : setter
            .getParameterTypes()[0]);
    }

    public String getName() {
        return name;
    }

    public Object get(Object instance) {
        return MethodUtil.invoke(getter, instance);
    }

    public void set(Object instance, Object value) {
        MethodUtil.invoke(setter, instance, value);
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public Class<?> getPropertyClass() {
        return propertyClass;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        T annotation = null;
        if (!isReadOnly()) {
            annotation = getter.getAnnotation(annotationClass);
        }
        if (annotation == null && !isWriteOnly()) {
            annotation = setter.getAnnotation(annotationClass);
        }
        return annotation;
    }

    @Override
    public boolean isWriteOnly() {
        return writeOnly;
    }

}

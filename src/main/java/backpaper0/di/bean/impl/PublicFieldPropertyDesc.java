package backpaper0.di.bean.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import backpaper0.di.bean.PropertyDesc;
import backpaper0.di.util.FieldUtil;

public class PublicFieldPropertyDesc implements PropertyDesc {

    private final String name;

    private final Field field;

    private final Class<?> propertyClass;

    public PublicFieldPropertyDesc(String name, Field field) {
        this.name = name;
        this.field = field;
        this.propertyClass = field.getType();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object get(Object instance) {
        return FieldUtil.get(field, instance);
    }

    @Override
    public void set(Object instance, Object value) {
        FieldUtil.set(field, instance, value);
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public boolean isWriteOnly() {
        return false;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    @Override
    public Class<?> getPropertyClass() {
        return propertyClass;
    }

}

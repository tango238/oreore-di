package backpaper0.di.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import backpaper0.di.bean.impl.AccessorPropertyDesc;
import backpaper0.di.bean.impl.DefaultBeanDesc;
import backpaper0.di.bean.impl.DefaultMethodDesc;
import backpaper0.di.bean.impl.PublicFieldPropertyDesc;
import backpaper0.di.util.BeanUtil;

public class BeanDescFactory {

    private static class AccessorPropertyDescBuilder {

        String name;

        Method getter;

        Method setter;

        AccessorPropertyDesc build() {
            return new AccessorPropertyDesc(name, getter, setter);
        }
    }

    public static BeanDesc getBeanDesc(Class<?> clazz) {
        List<MethodDesc> beanMethods = new ArrayList<MethodDesc>();
        Map<String, AccessorPropertyDescBuilder> builders = new LinkedHashMap<String, BeanDescFactory.AccessorPropertyDescBuilder>();
        Class<?> c = clazz;
        do {
            for (Method method : c.getDeclaredMethods()) {
                if (BeanUtil.isGetter(method)) {
                    getBuilder(builders, method).getter = method;
                } else if (BeanUtil.isSetter(method)) {
                    getBuilder(builders, method).setter = method;
                } else if (BeanUtil.isBeanMethod(method)) {
                    MethodDesc beanMethod = new DefaultMethodDesc(method);
                    beanMethods.add(beanMethod);
                }
            }
            c = c.getSuperclass();
        } while (c != null && !c.equals(Object.class));
        Field[] fields = clazz.getFields();
        List<PropertyDesc> propertyDescs = new ArrayList<PropertyDesc>(
            builders.size() + fields.length);
        for (AccessorPropertyDescBuilder builder : builders.values()) {
            PropertyDesc propertyDesc = builder.build();
            propertyDescs.add(propertyDesc);
        }
        for (Field field : fields) {
            PropertyDesc propertyDesc = new PublicFieldPropertyDesc(
                field.getName(),
                field);
            propertyDescs.add(propertyDesc);
        }
        BeanDesc beanDesc = new DefaultBeanDesc(
            clazz,
            propertyDescs,
            beanMethods);
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

}

package backpaper0.di.bean.impl;

import java.util.List;

import backpaper0.di.bean.BeanDesc;
import backpaper0.di.bean.BeanMethod;
import backpaper0.di.bean.PropertyDesc;

public class DefaultBeanDesc implements BeanDesc {

    private Class<?> beanClass;

    private List<PropertyDesc> propertyDescs;

    private List<BeanMethod> beanMethods;

    public DefaultBeanDesc(Class<?> beanClass,
            List<PropertyDesc> propertyDescs, List<BeanMethod> beanMethods) {
        this.beanClass = beanClass;
        this.propertyDescs = propertyDescs;
        this.beanMethods = beanMethods;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public List<PropertyDesc> getPropertyDescs() {
        return propertyDescs;
    }

    @Override
    public List<BeanMethod> getBeanMethods() {
        return beanMethods;
    }

}

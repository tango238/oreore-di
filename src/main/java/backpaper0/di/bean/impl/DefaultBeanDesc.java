package backpaper0.di.bean.impl;

import java.util.List;

import backpaper0.di.bean.BeanDesc;
import backpaper0.di.bean.PropertyDesc;

public class DefaultBeanDesc implements BeanDesc {

    private Class<?> beanClass;

    private List<PropertyDesc> propertyDescs;

    public DefaultBeanDesc(Class<?> beanClass, List<PropertyDesc> propertyDescs) {
        this.beanClass = beanClass;
        this.propertyDescs = propertyDescs;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public List<PropertyDesc> getPropertyDescs() {
        return propertyDescs;
    }

}

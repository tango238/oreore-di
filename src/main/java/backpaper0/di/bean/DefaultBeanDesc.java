package backpaper0.di.bean;

import java.util.List;

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

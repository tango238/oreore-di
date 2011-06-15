package backpaper0.di.bean;

import backpaper0.di.bean.impl.DefaultBeanDescProvider;
import backpaper0.di.util.ClassUtil;

public class BeanDescFactory {

    private static BeanDescProvider provider = createBeanDescProvider();

    public static BeanDesc getBeanDesc(Class<?> clazz) {
        BeanDesc beanDesc = provider.createBeanDesc(clazz);
        return beanDesc;
    }

    private static BeanDescProvider createBeanDescProvider() {
        final String beanDescProviderClassName = System.getProperty(
            BeanDescProvider.class.getSimpleName(),
            DefaultBeanDescProvider.class.getName());
        Class<BeanDescProvider> beanDescProviderClass = ClassUtil
            .forName(beanDescProviderClassName);
        return ClassUtil.newInstance(beanDescProviderClass);
    }

}

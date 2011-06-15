package backpaper0.di.bean;

import backpaper0.di.util.ClassUtil;

public class BeanDescFactory {

    private static final String PROVIDER_KEY = "BeanDescProvider";

    private static final String DEFAULT_PROVIDER_CLASS_NAME = "backpaper0.di.bean.impl.DefaultBeanDescProvider";

    private static BeanDescProvider provider = createBeanDescProvider();

    public static BeanDesc getBeanDesc(Class<?> clazz) {
        BeanDesc beanDesc = provider.createBeanDesc(clazz);
        return beanDesc;
    }

    static BeanDescProvider createBeanDescProvider() {
        final String beanDescProviderClassName = System.getProperty(
            PROVIDER_KEY,
            DEFAULT_PROVIDER_CLASS_NAME);
        Class<BeanDescProvider> beanDescProviderClass = ClassUtil
            .forName(beanDescProviderClassName);
        return ClassUtil.newInstance(beanDescProviderClass);
    }

}

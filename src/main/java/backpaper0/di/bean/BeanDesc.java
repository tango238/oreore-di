package backpaper0.di.bean;

import java.util.List;

public interface BeanDesc {

    Class<?> getBeanClass();

    List<PropertyDesc> getPropertyDescs();

    List<BeanMethod> getBeanMethods();

}

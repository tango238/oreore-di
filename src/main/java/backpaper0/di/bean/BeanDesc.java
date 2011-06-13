package backpaper0.di.bean;

import java.util.List;

/**
 * Beanのメタ情報です。
 * 
 * @author backpaper0
 *
 */
public interface BeanDesc {

    Class<?> getBeanClass();

    /**
     * Beanが持つすべてのプロパティのメタ情報を返します。
     * 
     * @return Beanが持つすべてのプロパティのメタ情報
     */
    List<PropertyDesc> getPropertyDescs();

    /**
     * Beanが持つプロパティのアクセサを除いたすべてのpublicメソッドのメタ情報を返します。
     * 
     * @return Beanが持つプロパティのアクセサを除いたすべてのpublicメソッドのメタ情報
     */
    List<MethodDesc> getMethodDescs();

}

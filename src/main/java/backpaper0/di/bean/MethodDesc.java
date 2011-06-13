package backpaper0.di.bean;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * メソッドのメタ情報です。
 * 
 * @author backpaper0
 *
 */
public interface MethodDesc {

    /**
     * メソッドを実行します。
     * 
     * @param instance
     * @param arguments
     * @return
     */
    Object invoke(Object instance, Object... arguments);

    /**
     * メソッドが持つアノテーションを返します。
     * 
     * <p>指定された型のアノテーションをプロパティが持っていなければ{@literal null}を返します。
     * 
     * @param <T>
     * @param annotationClass
     * @return
     */
    <T extends Annotation> T getAnnotation(Class<T> annotationClass);

    String getName();

    /**
     * 戻り値の型を返します。
     * 
     * @return 戻り値の型
     */
    Class<?> getReturnType();

    /**
     * すべての引数の型を返します。
     * 
     * @return すべての引数の型
     */
    List<Class<?>> getArgumentsTypes();
}

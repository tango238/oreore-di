package backpaper0.di.bean;

import java.lang.annotation.Annotation;

/**
 * プロパティのメタ情報です。
 * 
 * @author backpaper0
 *
 */
public interface PropertyDesc {

    String getName();

    /**
     * 値を返します。
     * 
     * <p>{@link #isWriteOnly()}が{@literal true}を返す場合、{@link UnsupportedOperationException}が投げられます。
     * 
     * @param instance
     * @return
     */
    Object get(Object instance);

    /**
     * 値を設定します。
     * 
     * <p>{@link #isReadOnly()}が{@literal true}を返す場合、{@link UnsupportedOperationException}が投げられます。
     * 
     * @param instance
     * @param value
     */
    void set(Object instance, Object value);

    /**
     * 読み取り専用であれば{@literal true}を返します。
     * 
     * @return
     */
    boolean isReadOnly();

    /**
     * 書き込み専用であれば{@literal true}を返します。
     * 
     * @return
     */
    boolean isWriteOnly();

    /**
     * プロパティが持つアノテーションを返します。
     * 
     * <p>指定された型のアノテーションをプロパティが持っていなければ{@literal null}を返します。
     * 
     * @param <T>
     * @param annotationClass
     * @return
     */
    <T extends Annotation> T getAnnotation(Class<T> annotationClass);

    Class<?> getPropertyClass();

}

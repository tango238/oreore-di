package backpaper0.di;

public class SingletonComponentFactory implements ComponentFactory {

    private Object singleton;

    public SingletonComponentFactory(Class<?> componentClass) {
        singleton = ClassUtil.newInstance(componentClass);
    }

    @Override
    public Object get() {
        return singleton;
    }

}

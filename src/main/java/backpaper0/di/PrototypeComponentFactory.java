package backpaper0.di;

public class PrototypeComponentFactory implements ComponentFactory {

    private Class<?> componentClass;

    public PrototypeComponentFactory(Class<?> componentClass) {
        this.componentClass = componentClass;
    }

    @Override
    public Object get() {
        return ClassUtil.newInstance(componentClass);
    }

}

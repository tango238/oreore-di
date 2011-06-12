package backpaper0.di.manager;


public class PrototypeComponentManager extends AbstractComponentManager {

    public PrototypeComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get() {
        Object component = createComponent();
        return component;
    }
}

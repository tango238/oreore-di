package backpaper0.di.manager;

public class SingletonComponentManager extends AbstractComponentManager {

    public SingletonComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get() {
        if (components.isEmpty()) {
            createComponent();
        }
        return components.iterator().next();
    }

}

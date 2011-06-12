package backpaper0.di.manager;

public class SingletonComponentManager extends AbstractComponentManager {

    private Object singleton;

    public SingletonComponentManager(Class<?> componentClass) {
        super(componentClass);
    }

    @Override
    public Object get() {
        if (singleton == null) {
            singleton = createComponent();
        }
        return singleton;
    }

}

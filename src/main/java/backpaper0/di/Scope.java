package backpaper0.di;

public enum Scope {

    SINGLETON {

        @Override
        public ComponentFactory createFactory(Class<?> componentClass) {
            return new SingletonComponentFactory(componentClass);
        }

    },
    PROTOTYPE {

        @Override
        public ComponentFactory createFactory(Class<?> componentClass) {
            return new PrototypeComponentFactory(componentClass);
        }

    };

    public abstract ComponentFactory createFactory(Class<?> componentClass);
}

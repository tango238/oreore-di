package backpaper0.di;

public enum Scope {

    SINGLETON {

        @Override
        public ComponentManager createComponentManager(Class<?> componentClass) {
            return new SingletonComponentManager(componentClass);
        }

    },
        PROTOTYPE {

            @Override
            public ComponentManager createComponentManager(
                    Class<?> componentClass) {
                return new PrototypeComponentManager(componentClass);
            }

        };

    public abstract ComponentManager createComponentManager(
            Class<?> componentClass);
}

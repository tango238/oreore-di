package backpaper0.di.scope.impl;

import backpaper0.di.manager.ComponentManager;
import backpaper0.di.manager.impl.PrototypeComponentManager;
import backpaper0.di.manager.impl.SingletonComponentManager;
import backpaper0.di.scope.Scope;

public enum DefaultScopes implements Scope {

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

}

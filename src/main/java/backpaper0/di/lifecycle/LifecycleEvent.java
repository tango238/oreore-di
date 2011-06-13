package backpaper0.di.lifecycle;

import java.util.EventObject;

public class LifecycleEvent extends EventObject {

    public LifecycleEvent(Object source) {
        super(source);
    }
}

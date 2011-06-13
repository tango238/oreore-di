package backpaper0.di.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LifecycleManager {

    private Map<Lifecycle, List<LifecycleListener>> listenersMap = new HashMap<Lifecycle, List<LifecycleListener>>();

    public void addLifecycleListener(Lifecycle lifecycle,
            LifecycleListener listener) {
        if (!listenersMap.containsKey(lifecycle)) {
            listenersMap.put(lifecycle, new ArrayList<LifecycleListener>());
        }
        listenersMap.get(lifecycle).add(listener);
    }

    public void fireLifecycleEvent(Lifecycle lifecycle, Object source) {
        LifecycleEvent lifecycleEvent = new LifecycleEvent(source);
        if (listenersMap.containsKey(lifecycle)) {
            for (LifecycleListener listener : listenersMap.get(lifecycle)) {
                listener.apply(lifecycleEvent);
            }
        }
    }
}

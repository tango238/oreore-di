package backpaper0.di;

public interface ComponentManager {

    Object get(Injector injector);

    void destroy();

}

package backpaper0.di.inject;


public interface Injector {

    <T> void inject(T component);

}

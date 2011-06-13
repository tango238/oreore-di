package backpaper0.di.config;

import backpaper0.di.inject.Injector;
import backpaper0.di.register.RegisterRule;

public interface Configuration {

    Injector createInjector();

    RegisterRule getRegisterRule();

}

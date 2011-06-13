package backpaper0.di.config;

import backpaper0.di.inject.Injector;
import backpaper0.di.inject.impl.DefaultInjector;
import backpaper0.di.register.RegisterRule;

public class Configuration {

    private RegisterRule registerRule;

    public Injector createInjector() {
        return new DefaultInjector();
    }

    public void setRegisterRule(RegisterRule registerRule) {
        this.registerRule = registerRule;
    }

    public RegisterRule getRegisterRule() {
        return registerRule;
    }
}

package backpaper0.di.config.impl;

import backpaper0.di.config.Configuration;
import backpaper0.di.inject.Injector;
import backpaper0.di.inject.impl.DefaultInjector;
import backpaper0.di.register.RegisterRule;

public class DefaultConfiguration implements Configuration {

    private RegisterRule registerRule;

    @Override
    public Injector createInjector() {
        return new DefaultInjector();
    }

    public void setRegisterRule(RegisterRule registerRule) {
        this.registerRule = registerRule;
    }

    @Override
    public RegisterRule getRegisterRule() {
        return registerRule;
    }
}

package backpaper0.di.testing;

import backpaper0.di.annotation.Inject;

public class InjectBean3 {

    private InjectBean4 injectBean4;

    @Inject
    public void setInjectBean4(InjectBean4 injectBean4) {
        this.injectBean4 = injectBean4;
    }

    public InjectBean4 getInjectBean4() {
        return injectBean4;
    }
}

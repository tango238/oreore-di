package backpaper0.di.testing;

import backpaper0.di.Inject;

public class InjectBean4 {

    private InjectBean2 injectBean2;

    @Inject
    public void setInjectBean2(InjectBean2 injectBean2) {
        this.injectBean2 = injectBean2;
    }

    public InjectBean2 getInjectBean2() {
        return injectBean2;
    }

}

package backpaper0.di.testing;

public class IsSetterBean {

    public void setAaa(String aaa) {
    }

    public void setB(int b) {
    }

    // setterでない
    public void setccc(String c) {
    }

    // setterでない
    public int setDdd(int d) {
        return 0;
    }

    // setterでない
    public void setEee(String e, int e2) {
    }

    // setterでない
    public void setFff() {
    }

    // 可視性がpublicでない
    private void setGgg(String ggg) {
    }

    // インスタンスメソッドでない
    public static void setHhh(String hhh) {
    }
}

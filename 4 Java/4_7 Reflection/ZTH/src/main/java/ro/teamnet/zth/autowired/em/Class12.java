package ro.teamnet.zth.autowired.em;

import ro.teamnet.zth.autowired.annotations.MyAutowired;

public class Class12 {

    public Class12() throws IllegalAccessException {
        AutowiredUtils.handleAutowiring(this);
    }
    @MyAutowired
    public Class1 class1;
    @MyAutowired(value = "smaller")
    public Class2 class2;

    public Class1 getClass1() {
        return class1;
    }

    public Class2 getClass2() {
        return class2;
    }
}

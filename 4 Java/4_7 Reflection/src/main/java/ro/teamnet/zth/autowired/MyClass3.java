package ro.teamnet.zth.autowired;

public class MyClass3 {
    @MyAutowired(324)
    MyClass1 myClass1 = new MyClass1();

    @MyAutowired()
    MyClass2 myClass2 = new MyClass2();
}

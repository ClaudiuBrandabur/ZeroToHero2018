package ro.teamnet.zth.autowired;

@MyQualifier(123)
public class MyClass1 implements Interface {
    @Override
    public void myMethod() {
        System.out.println("Void method in first class");
    }
}

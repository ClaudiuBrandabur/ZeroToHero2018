package ro.teamnet.zth.autowired;

public class Main {

    public static void main() {

        MyClass1 myClass1 = new MyClass1();
        MyClass2 myClass2 = new MyClass2();

        MyClass3 instance1 = new MyClass3();
        MyClass3 instance2 = new MyClass3();
        instance1.myClass1.myMethod();
        instance2.myClass2.myMethod();
    }

}

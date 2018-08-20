package ro.teamnet.zth.autowired.em;

public class Main{

    public static void main(String[] args) throws IllegalAccessException {
        Class1 class1 = new Class1();
        Class2 class2 = new Class2();
        Class12 clazz1 = new Class12();
        clazz1.getClass1().location();
        clazz1.getClass2().location();
        Class12 clazz2 = new Class12();
        clazz2.getClass1().location();
        clazz2.getClass2().location();
    }
}

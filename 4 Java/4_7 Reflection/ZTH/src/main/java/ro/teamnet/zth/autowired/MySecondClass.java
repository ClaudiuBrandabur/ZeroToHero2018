package ro.teamnet.zth.autowired;

@MyQualifier(5)
public class MySecondClass implements MyInterface{
    @Override
    public void print() {
        System.out.println("My Second Class");
    }


}

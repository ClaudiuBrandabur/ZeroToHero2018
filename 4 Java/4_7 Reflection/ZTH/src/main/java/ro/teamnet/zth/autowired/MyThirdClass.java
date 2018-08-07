package ro.teamnet.zth.autowired;

public class MyThirdClass {

    @MyAutowired
    public MyFirstClass firstClass;

    @MyAutowired(6)
    public MySecondClass secondClass;
}

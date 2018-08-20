package ro.teamnet.zth.autowired.em;

import ro.teamnet.zth.autowired.annotations.MyQualifier;

@MyQualifier(value = "bigger")
public class Class1 implements Inter {

    public Class1() {
        AutowiredUtils.updateQualifiers(this);
    }
    @Override
    public void location() {
        System.out.println("CLASS 1");
    }
}

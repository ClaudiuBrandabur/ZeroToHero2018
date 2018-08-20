package ro.teamnet.zth.autowired.em;

public class Class2 implements Inter {
    public Class2() {
        AutowiredUtils.updateType(this);
    }
    @Override
    public void location() {
        System.out.println("CLASS 2");
    }
}

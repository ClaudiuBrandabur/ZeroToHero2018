package ro.teamnet.zerotohero.reflection.demoobjects;

public class DemoClass {

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DemoClass() {
        this.name = "Sorin";
        this.id = 1;
    }

    public String method1(){
        return "The name is: " + name + " and the id is: " + id;
    }
    public String getDeclaredField(String name) {
        return name;
    }

}

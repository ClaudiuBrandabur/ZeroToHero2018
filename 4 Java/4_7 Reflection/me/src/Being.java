public class Being {

    private String name = "My Creature";
    private int age = 5;

    public Being() {
        System.out.println("new born crature");
    }

    public Being(String name){
        this.name = name;
    }

    public void print(){
        System.out.println("yeey Print me !");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

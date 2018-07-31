package exercise3;

public class MyStudent1 extends Student {

    public MyStudent1(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;

        MyStudent1 s = (MyStudent1)obj;
        if(!getFirstName().equals(s.getFirstName())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getFirstName().hashCode();
    }
}

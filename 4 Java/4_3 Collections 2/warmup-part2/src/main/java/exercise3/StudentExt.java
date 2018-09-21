package exercise3;

public class StudentExt extends StudentExt2 {

    String firstName;
    String lastName;


    @Override
    public int hashCode(){
        return firstName.hashCode();
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        else return false;
    }

}

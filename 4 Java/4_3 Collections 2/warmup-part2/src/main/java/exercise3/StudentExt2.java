package exercise3;

public class StudentExt2 extends StudentExt3 {

    String firstName;
    String lastName;

    @Override
    public int hashCode(){
        return firstName.hashCode();
    }

    @Override
    public boolean equals (Object o){
        Student student = (Student) o;
        return firstName == student.firstName && lastName == student.lastName;
    }
}

package exercise3;

public class StudentExt4 {

    String firstName;
    String lastName;

    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Student student = (Student) o;
        return firstName == student.firstName && lastName == student.lastName;

    }

}

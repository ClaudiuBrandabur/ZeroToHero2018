package exercise3;

public class Exercise3d extends Student {

    public Exercise3d(String firstName, String lastName) {
        super(firstName, lastName);
    }
    //TODO a)
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        Student student = (Student) o;
        if (this.getFirstName().equals(student.getFirstName()) && this.getLastName().equals(student.getLastName()))
            return true;
        return false;
    }

}
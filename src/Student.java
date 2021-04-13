import java.util.Date;

public class Student {
    private String id;
    private String name;
    private String birthdate;

    public Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " ID: " + id + " DOB: " + birthdate;
    }
}

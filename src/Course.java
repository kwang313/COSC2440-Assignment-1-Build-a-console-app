public class Course {
    private String id;
    private String name;
    private int credit;

    public Course(String id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + "Credit: " +credit;
    }
}

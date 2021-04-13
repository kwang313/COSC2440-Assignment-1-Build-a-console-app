import java.util.ArrayList;

public interface StudentEnrollmentManager {

    public void add(StudentEnrollment studentEnrollment);
    public void update(Student stuTarget,Course courseCur, Course courseTar );
    public void getOne(int position);
    public void getAll();
    public void getAllCourse(String stuID, String sem);
	public void getAllStudent(String courseID, String sem);
    public void delete(Student stuTarget, Course courseTarget);
    public void getCourseinSem(String sem);
    }


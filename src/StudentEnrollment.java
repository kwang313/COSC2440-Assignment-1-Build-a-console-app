
import java.util.*;

public class StudentEnrollment implements StudentEnrollmentManager {
	private Student stu;
	private Course course;
	private String semester;
	private ArrayList<StudentEnrollment> enrollments = new ArrayList<>();

	public StudentEnrollment(Student stu, Course course, String semester) {
		this.stu = stu;
		this.course = course;
		this.semester = semester;
	}

	public StudentEnrollment() {
		this.semester = ("THIS IS A MANAGERMENT VARIABLE");
	}

	public Student getStudent() {
		return stu;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course newCourse) {
		this.course = newCourse;
	}

	public String getSemester() {
		return semester;
	}

	public ArrayList<StudentEnrollment> getEnrollments() {
		return enrollments;
	}

	@Override
	public String toString(){
		return this.getStudent().getId() +" "+ this.getStudent().getName()+ "  "+this.getCourse().getId() + "  "+this.getCourse().getName() +"  semester:"+ this.getSemester();
	}

	@Override
	public void add(StudentEnrollment newStudentEnrollment) {
		enrollments.add(newStudentEnrollment);
	}

	@Override
	public void delete(Student stuTarget, Course courseTarget) {
		int pos = 0;
		for (StudentEnrollment enrollment : enrollments){
			if(enrollment.getCourse().getId().equals(courseTarget.getId())
					&& enrollment.getStudent().getId().equals(stuTarget.getId())) {
				break;
			}
			pos +=1;
		}
		enrollments.remove(pos);
	}

	@Override
	public void update(Student stuTarget,Course courseCur, Course courseTarget ){
		int pos = 0;
		for (StudentEnrollment enrollment : enrollments){
			if(enrollment.getCourse().getId().equals(courseCur.getId())
					&& enrollment.getStudent().getId().equals(stuTarget.getId())) {
				break;
			}
			pos +=1;
		}
		enrollments.get(pos).setCourse(courseTarget);;
	}

	@Override
	public void getAll() {
		for (StudentEnrollment enrollment : enrollments){
			System.out.println(enrollment.toString());
		}

	}

	@Override
	public void getOne(int position) {
		for (int i = 0; i < enrollments.size(); i++) {
			if(i == position){
				enrollments.get(i).toString();
			}
		}
	}

	@Override
	public void getAllCourse(String stuID, String sem) {
		boolean found = false;
		for(StudentEnrollment enrollment : enrollments) {
			if(enrollment.getSemester().equals(sem)
					&& enrollment.getStudent().getId().equals(stuID)) {
				System.out.println(enrollment.getCourse().toString());
				found = true;

			}

		}
		if(!found) {
			System.out.println("NO DATA TO SHOW");
		}
	}

	@Override
	public void getAllStudent(String courseID, String sem) {
		boolean found = false;
		for(StudentEnrollment enrollment : enrollments) {
			if(enrollment.getSemester().equals(sem)
					&& enrollment.getCourse().getId().equals(courseID)) {
				System.out.println(enrollment.getStudent().toString());
				found = true;

			}

		}
		if(!found) {
			System.out.println("NO DATA TO SHOW");
		}

	}
	
	public void getCourseinSem(String sem) {
		boolean found = false;
		ArrayList<String> printed = new ArrayList<>();
		for(StudentEnrollment enrollment : enrollments) {
			if(enrollment.getSemester().equals(sem) 
					&& !printed.contains(enrollment.getCourse().getId())) {
				System.out.println(enrollment.getCourse().toString());
				printed.add(enrollment.getCourse().getId());
				found = true;

			}

		}
		if(!found) {
			System.out.println("NO DATA TO SHOW");
		}

	}

}

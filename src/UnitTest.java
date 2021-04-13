import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class UnitTestTest {

	StudentEnrollment master = new StudentEnrollment();
	Student stu1 = new Student("s1", "James Tran", "22/7/2000");
    Student stu2 = new Student("s2", "Kwang Nguyen","12/5/1999");
    Student stu3 = new Student("s3", "Chloe Le", "31/3/2000");
    Course course1 = new Course("c1", "Photography", 12);
    Course course2 = new Course("c2", "Web Programming", 12);
    Course course3 = new Course("c3", "Database", 12);
    StudentEnrollment e1 = new StudentEnrollment(stu1, course1,"sem1");
    StudentEnrollment e2 = new StudentEnrollment(stu1, course2,"sem2");
    StudentEnrollment e3 = new StudentEnrollment(stu1, course3,"sem3");
    ArrayList<StudentEnrollment> testList = new ArrayList<>();

	@Test
	void testGetter() {
	    testList.add(e1);
	    testList.add(e2);
	    testList.add(e3);
	    master.add(e1);
	    master.add(e2);
	    master.add(e3);
        Assert.assertEquals(e1.getStudent(), stu1);
        Assert.assertEquals(e1.getCourse(), course1);
        Assert.assertEquals(e1.getSemester(), "sem1");
        Assert.assertEquals(e1.getSemester(), "sem1");
        Assert.assertEquals(master.getEnrollments(), testList);    

	}
	@Test
	void testCRUD() {
		//ADD
		master.add(e1);
	    master.add(e2);
	    master.add(e3);
	    testList.add(e1);
	    testList.add(e2);
	    testList.add(e3);
        Assert.assertEquals(master.getEnrollments(), testList);   
        
		//REMOVE
        testList.remove(1);
        master.delete(e2.getStudent(), e2.getCourse());
        StudentEnrollment e4 = new StudentEnrollment(stu3, course1,"sem3");
        StudentEnrollment e5 = new StudentEnrollment(stu3, course2,"sem3");
        testList.add(e4);
        master.add(e5);
        System.out.println("master:");
        master.update(stu3, course2, course1);
        master.getAll();

        System.out.println("List:");
        String m = "";
        for (StudentEnrollment e : testList) {
            m += e.toString()+"\n";
        }
        //UPDATE
        String t = "";
        for (StudentEnrollment e : master.getEnrollments()) {
            t += e.toString()+"\n";
        }
        Assert.assertEquals(t, m);    

	}

}

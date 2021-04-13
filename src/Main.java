import java.rmi.UnexpectedException;
import java.sql.SQLOutput;
import java.util.*;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException; 

public class Main {

	public static void main (String[] args) {
		ArrayList<String> semList = new ArrayList<>();
		ArrayList<String> courseListId = new ArrayList<>();
		ArrayList<Course> courseList = new ArrayList<>();
		StudentEnrollment manager = new StudentEnrollment();

		StudentEnrollment test = null;

		//READ CSV
		String line = "";  
		String splitBy = ",";  
		String fileName = "";
		Scanner scan = new Scanner(System.in);
		boolean found = false;
		int choice = 0;
		while(!found) {
			System.out.println("Welcome to the Student Enrollment system!");
			System.out.println("   1.Load a CSV file");
			System.out.println("   2.Load defaul csv file");
			System.out.println("   3.EXIT");
			choice = scan.nextInt();
			switch(choice) {
			case 3:{
				System.out.println("Exiting Program...");
				System.exit(0);
				break;
			}
			case 1:{
				System.out.print("ENTER YOUR FILE NAME:");
				scan.nextLine();
				fileName = scan.nextLine();
				break;
			}
			case 2:{
				fileName = "default";
				break;
			}
			}
			try   
			{  

				//parsing a CSV file into BufferedReader class constructor  
				BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"));  
				while ((line = br.readLine()) != null)   //returns a Boolean value  
				{  
					String[] data = line.split(splitBy);    // use comma as separator  
					Student newStudent = new Student(data[0],data[1],data[2]);
					Course newCourse = new Course(data[3],data[4], Integer.parseInt(data[5]));
					if(!courseListId.contains(data[3])) {
						courseListId.add(data[3]);
						courseList.add(newCourse);
					}
					if(!semList.contains(data[6])) {
						semList.add(data[6]);
					}
					manager.add(new StudentEnrollment(newStudent, newCourse, data[6]));
				}
				found = true;
				br.close();
			}   
			catch (IOException e)   
			{  
				System.out.println("ERROR NO FILE WITH THE NAME "+fileName+".csv FOUND!") ;
			} 

		}
		choice = 0;
		//Display the console app's menu and receive input from user
		while (true) {
			System.out.println("");
			System.out.println("Welcome to the Student Enrollment system!");
			System.out.println("    1. EXIT");
			System.out.println("    2. ENROLL A STUDENT");
			System.out.println("    3. UPDATE AN ENROLLMENT OF A STUDENT");
			System.out.println("    4. PRINT ALL COURSES OF 1 STUDENT IN A SEMESTER");
			System.out.println("    5. PRINT ALL STUDENTS OF 1 COURSE IN A SEMESTER");
			System.out.println("    6. PRINT ALL COURSES IN A SEMESTER");
			System.out.println("    7. PRINT ALL ENROLLMENT DETAILS");

			System.out.print("Please choose the option's number: ");
			choice = scan.nextInt();


			switch (choice) {
			// If the input from the user doesn't match any cases then runs default case
			default:
				System.out.println("This is not a valid option, please choose again!");
				break;
				// Exits the system if the user chooses option 1
			case 1:
				System.out.println("Exiting Program...");
				System.exit(0);
				break;
			case 2:
			{
				scan.nextLine();
				String stuID;
				String stuDOB;
				String stuName;
				String sem;
				System.out.println("----Enrolling a student----");
				System.out.println("Please input the student's ID: ");
				stuID = scan.nextLine().toUpperCase();

				System.out.println("Please input the student's Name: ");
				stuName = scan.nextLine();
				System.out.println("Please input the student's D.O.B: ");
				stuDOB = scan.nextLine();
				Student newStudent = new Student(stuID, stuName, stuDOB);
				System.out.println("----AVAILABLE SEMESTERS----");
				for(String semester : semList){
					System.out.println(semester);
				}
				System.out.print("Please select a semester you want to enroll in: ");
				sem = scan.nextLine().toUpperCase();
				while(!semList.contains(sem)){
					System.out.print("Invalid semester");
					System.out.print("Please select a semester you want to enroll in: ");
					sem = scan.nextLine().toUpperCase();
				}
				System.out.println("----AVAILABLE COURSES----");
				int courseP = 0;
				for(Course course : courseList){
					courseP += 1;
					System.out.println(courseP +". "+ course.toString());
				}
				System.out.print("Please select a course you want to enroll in: ");
				courseP = scan.nextInt();
				while((courseP > courseList.size()) || (courseP < 1)){
					System.out.println("Invalid course");
					System.out.print("Please select a course you want to enroll in: ");
					courseP = scan.nextInt();
				}
				Course newCourse = courseList.get(courseP-1);
				StudentEnrollment newEnrollment = new StudentEnrollment(newStudent, newCourse, sem);
				manager.add(newEnrollment);
				System.out.print("---ENROLLED!---\n\n");

				break;
			}
			case 3:
			{
				Student curStudent = null;
				String stuID;
				int check = 0;
				stuID = scan.nextLine();
				System.out.println("----Updating a student's enrollment----");
				System.out.print("Please input the student's ID: ");
				stuID = scan.nextLine().toUpperCase();
				for (StudentEnrollment enrollment : manager.getEnrollments()){
					if (enrollment.getStudent().getId().equals(stuID)){
						curStudent = enrollment.getStudent();
						check += 1;
					}
				}
				if (check == 0){
					System.out.println("No student with ID: " +stuID +" found!");
					break;
				}
				else {
					System.out.println("\n==="+ stuID +" information"+"===");
					for (StudentEnrollment enrollment : manager.getEnrollments()){
						if (enrollment.getStudent().getId().equals(stuID)){
							System.out.println(enrollment.getStudent().toString());
							break;
						}
					}
					int courseP = 1;
					System.out.println("==="+ stuID +" enrollment information"+"===");
					for (StudentEnrollment enrollment : manager.getEnrollments()){
						if (enrollment.getStudent().getId().equals(stuID)){
							System.out.println(courseP+" "+enrollment.getCourse().toString() +" Semester:" + 
									enrollment.getSemester().toString()+"\n");
							courseP += 1;
						}
					}
				}
				int action = 0;
				System.out.println("1. CHANGE AN ENROLLMENT");
				System.out.println("2. ENROLL IN A COURSE");
				System.out.println("3. DROP A COURSE");
				System.out.println("4. EXIT");
				System.out.print("Please choose the option's number:");

				action = scan.nextInt();
				while(action > 5 || action < 0) {
					System.out.println("Invalid action");
					System.out.print("Please choose the option's number:");
					action = scan.nextInt();

				}
				switch (action){
				case 4:{
					break;
				}

				case 1:{
					ArrayList<Course> available = new ArrayList<>();
					ArrayList<Course> enrolled = new ArrayList<>();
					ArrayList<String> enrolledId = new ArrayList<>();
					ArrayList<String> holder = new ArrayList<>();


					int courseP = 1;
					System.out.println("==="+ stuID +" enrollment information"+"===");
					for (StudentEnrollment enrollment : manager.getEnrollments()){
						if (enrollment.getStudent().getId().equals(stuID)){
							System.out.println(courseP+" "+enrollment.getCourse().toString() +" Semester:" + 
									enrollment.getSemester().toString()+"\n");
							enrolled.add(enrollment.getCourse());
							enrolledId.add(enrollment.getCourse().getId());
							courseP += 1;
						}
					}

					for (String courseId: courseListId) {
						if (!enrolledId.contains(courseId)) {
							holder.add(courseId);

						}

					}
					for (Course course : courseList) {
						if(holder.contains(course.getId())) {
							available.add(course);
						}
					}

					System.out.print("Please select a course you want to change: ");
					courseP = scan.nextInt();

					while((courseP > enrolled.size() ) || (courseP < 1)){
						System.out.println("Invalid course");
						System.out.print("Please select a course you want to change: ");
						courseP = scan.nextInt();
					}
					System.out.println("----AVAILABLE COURSES----");
					int pos = 0;
					for(Course course : available){
						pos += 1;
						System.out.println(pos +". "+ course.toString());
					}
					System.out.print("Please select a course you want to enroll in: ");
					pos = scan.nextInt();
					while((pos > available.size() ) || (pos < 1)){
						System.out.println("Invalid course");
						System.out.print("Please select a course you want to enroll in: ");
						pos = scan.nextInt();
					}
					String conf = "";
					System.out.print("Are you sure you want to change: "+enrolled.get(courseP-1).getId()+" "+enrolled.get(courseP-1).getName()+""
							+" to "+available.get(pos-1).getId()+" "+available.get(pos-1).getName()
							+ "\n(Y/N):");
					conf = scan.next();
					if(conf.equalsIgnoreCase("Y")) {
						manager.update(curStudent, enrolled.get(courseP-1), available.get(pos-1));
						System.out.print("---Enrollment UPDATED---");

					}
					else {
						System.out.print("---Cancel---");
					}
					break;

				}


				case 2:{
					//Semester
					String sem;
					sem = scan.nextLine();
					System.out.println("----AVAILABLE SEMESTERS----");
					for(String semester : semList){
						System.out.println(semester);
					}
					System.out.print("Please select a semester you want to enroll in: ");
					sem = scan.nextLine().toUpperCase();
					while(!semList.contains(sem)){
						System.out.print("Invalid semester");
						System.out.print("Please select a semester you want to enroll in: ");
						sem = scan.nextLine().toUpperCase();
					}                            

					//Course
					System.out.println("----AVAILABLE COURSES----");
					int courseP = 0;
					for(Course course : courseList){
						courseP += 1;
						System.out.println(courseP +". "+ course.toString());
					}
					System.out.print("Please select a course you want to enroll in: ");
					courseP = scan.nextInt();
					while((courseP > courseList.size() ) || (courseP < 1)){
						System.out.println("Invalid course");
						System.out.print("Please select a course you want to enroll in: ");
						courseP = scan.nextInt();
					}
					Course newCourse = courseList.get(courseP-1);
					StudentEnrollment newEnrollment = new StudentEnrollment(curStudent, newCourse, sem);
					manager.add(newEnrollment);
					System.out.print("---ENROLLED!---\n\n");
					break;
				}
				case 3:{
					ArrayList<Course> enrolled = new ArrayList<>();
					int courseP = 1;
					System.out.println("==="+ stuID +" enrollment information"+"===");
					for (StudentEnrollment enrollment : manager.getEnrollments()){
						if (enrollment.getStudent().getId().equals(stuID)){
							System.out.println(courseP+" "+enrollment.getCourse().toString() +" Semester:" + 
									enrollment.getSemester().toString()+"\n");
							enrolled.add(enrollment.getCourse());
							courseP += 1;
						}
					}
					System.out.print("Please select a course you want to drop in: ");
					courseP = scan.nextInt();
					while((courseP > enrolled.size() ) || (courseP < 1)){
						System.out.println("Invalid course");
						System.out.print("Please select a course you want to enroll in: ");
						courseP = scan.nextInt();
					}
					String conf;
					System.out.print("Are you sure you want to drop: "+enrolled.get(courseP-1).getId()+" "+enrolled.get(courseP-1).getName()+"\n(Y/N):");
					conf = scan.next();
					if(conf.equalsIgnoreCase("Y")) {
						manager.delete(curStudent, enrolled.get(courseP-1));
						System.out.print("---Succesfully dropped: "+enrolled.get(courseP-1).getId()+" "+enrolled.get(courseP-1).getName()+"---");

					}
					else {
						System.out.print("---Cancel dropping---");
					}
					break;

				}

				}
				break;
			}





			case 4:{
				String stuID;
				stuID = scan.nextLine();
				String sem;
				System.out.println("Please input the student's ID: ");
				stuID = scan.nextLine().toUpperCase();
				System.out.println("----AVAILABLE SEMESTERS----");
				for(String semester : semList){
					System.out.println(semester);
				}
				System.out.print("Please select a semester you want to see: ");
				sem = scan.nextLine().toUpperCase();
				while(!semList.contains(sem)){
					System.out.print("Invalid semester");
					System.out.print("Please select a semester you want see: ");
					sem = scan.nextLine().toUpperCase();
				}   
				System.out.println("---Courses of "+stuID+" in semester " + sem+"---");
				manager.getAllCourse(stuID, sem);
				break;
			}
			case 5:{
				String courseID;
				courseID = scan.nextLine();
				String sem;
				System.out.println("----AVAILABLE SEMESTERS----");
				for(String semester : semList){
					System.out.println(semester);
				}
				System.out.print("Please select a semester you want to see: ");
				sem = scan.nextLine().toUpperCase();
				while(!semList.contains(sem)){
					System.out.print("Invalid semester");
					System.out.print("Please select a semester you want see: ");
					sem = scan.nextLine().toUpperCase();
				}   

				System.out.println("----AVAILABLE COURSES----");
				for(Course course : courseList){
					System.out.println("ID: "+course.getId()
					+"   Name: "+course.getName());
				}
				System.out.print("Please select a course you want to see: ");
				courseID = scan.nextLine().toUpperCase();
				while(!courseListId.contains(courseID)){

					System.out.print("Invalid course ID");
					System.out.print("Please select a course you want see: ");
					courseID = scan.nextLine().toUpperCase();
				}   
				System.out.println("----Current student(s) enrolling in "+ courseID +" in semseter "+sem+"----");
				manager.getAllStudent(courseID, sem);
				break;

			}
			case 6:{
				String sem;
				scan.nextLine();
				System.out.println("----AVAILABLE SEMESTERS----");
				for(String semester : semList){
					System.out.println(semester);
				}
				System.out.print("Please select a semester you want to see: ");
				sem = scan.nextLine().toUpperCase();
				while(!semList.contains(sem)){
					System.out.print("Invalid semester");
					System.out.print("Please select a semester you want see: ");
					sem = scan.nextLine().toUpperCase();
				}   

				System.out.println("----AVAILABLE COURSES----");
				manager.getCourseinSem(sem);
				break;
			}
			case 7:{
				manager.getAll();
				break;
			}

			}
		}
	}

}






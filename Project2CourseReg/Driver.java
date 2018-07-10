import java.util.ArrayList;

public class Driver {
	private static final int MAX_COURSE_CAPACITY = 5;
	
	public static void main(String[] args) {
		// create six students
		Student student1 = new Student("Magic", "Mike", "1", true);
		Student student2 = new Student("Bob", "Marley", "2", true);
		Student student3 = new Student("Caesar", "Chavez", "3", true);
		Student student4 = new Student("Billy", "Jean", "4", true);
		Student student5 = new Student("Marilyn", "Monroe", "5", true);
		Student student6 = new Student("Janet", "Jackson", "6", true);
		
		// create a course that can hold five students
		Student[] emptyClass = new Student[0];
		Course biology = new Course("Biology", MAX_COURSE_CAPACITY, emptyClass);
		
		// print the course text representation
		System.out.println(biology);
		
		// add five students to the course
		biology.addStudent(student1);
		biology.addStudent(student2);
		biology.addStudent(student3);
		biology.addStudent(student4);
		biology.addStudent(student5);
		
		// try to add the sixth student and print a message when the add fails		
		biology.addStudent(student6);
		
		// print the course text representation
		System.out.println(biology);
		
		// drop a student from the course
		biology.dropStudent(student5);
		
		// print the course text representation
		System.out.println(biology);
		
		// try again to add the sixth student (which should now succeed)
		biology.addStudent(student6);
		
		// print the course text representation
		System.out.println(biology);
		
		// EXTRA CREDIT
		// create a course that can hold five students
		ArrayList<Student> emptyClassExtra = new ArrayList<Student>();
		emptyClassExtra.clear();
		CourseAL math = new CourseAL("Math", MAX_COURSE_CAPACITY, emptyClassExtra);
		
		// print the course text representation
		System.out.println(math);
		
		// add five students to the course
		math.addStudent(student1);
		math.addStudent(student2);
		math.addStudent(student3);
		math.addStudent(student4);
		math.addStudent(student5);
		
		// try to add the sixth student and print a message when the add fails		
		math.addStudent(student6);
		
		// print the course text representation
		System.out.println(math);
		
		// drop a student from the course
		math.dropStudent(student5);
		
		// print the course text representation
		System.out.println(math);
		
		// try again to add the sixth student (which should now succeed)
		math.addStudent(student6);
		
		// print the course text representation
		System.out.println(math);
	}
}

import java.util.ArrayList;

public class CourseAL {
	private String name;
	private int capacity;
	private ArrayList<Student> students;

	public CourseAL(String name, int capacity, ArrayList<Student> students) {
		this.name = name;
		this.capacity = capacity;
		this.students = students;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public int getNumberOfStudentsEnrolled( ) {
		return students.size();
	}
	
	public String allStudents() {
		String allStudents = "";
		if (getNumberOfStudentsEnrolled() > 0) {
			for (Student student : students) {
				allStudents += student.toString();
			}
		} 
		return allStudents;
	}
	
	public void setName(String name) {
		if (!name.isEmpty()) {
			this.name = name;
		}
	}
	
	public void setCapacity(int capacity) {
		if (capacity > 0) {
			this.capacity = capacity;
		}
	}
	
	public void setStudents(ArrayList<Student> students) {
		if (students.size() > 0) {
			this.students = students;
		}
	}
	
	public boolean isCourseFull() {
		if (students.size() >= capacity) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addStudent(Student student) {
		boolean canAddStudent = false;
		if (!student.getTuitionPaid()) {
			System.out.println("Student must pay tuition in order to enroll.");
		} else if (isCourseFull()) {
			System.out.println("Course is full.");
		} else {
			canAddStudent = true;
		}
		if (canAddStudent) {
			students.add(student);
		} 
		return canAddStudent;
	}

	public boolean dropStudent(Student student) {
		boolean canDropStudent = false;
		int dropIndex = 0;
		if (students.size() > 0) {
			String studentToDrop = student.toString();
			for(int i = 0; i < students.size(); i++) {
				String currentStudent = students.get(i).toString();
				if (studentToDrop.equals(currentStudent)) {
					canDropStudent = true;
					dropIndex = i;
					break;
				}
			}
		}
		if (canDropStudent) {
			students.remove(dropIndex);
		}
		return canDropStudent;
	}
	
	public String toString() {
		String s = getName() + " (" + getCapacity() + " student capacity)\n";
		s += "Enrollment: " + getNumberOfStudentsEnrolled() + "\n";
		s += allStudents();
		return s;
	}
}
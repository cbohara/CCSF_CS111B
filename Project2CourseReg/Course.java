public class Course {
	private String name;
	private int capacity;
	private Student[] students;

	public Course(String name, int capacity, Student[] students) {
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
	
	public Student[] getStudents() {
		return students;
	}
	
	public int getNumberOfStudentsEnrolled( ) {
		return students.length;
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
	
	public void setStudents(Student[] students) {
		if (students.length > 0) {
			this.students = students;
		}
	}
	
	public boolean isCourseFull() {
		if (students.length >= capacity) {
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
			Student[] newStudents = new Student[students.length + 1];
			for (int i = 0; i < newStudents.length; i++) {
				if (i < students.length) {
					newStudents[i] = students[i];
				} else {
					newStudents[i] = student;
				}
			}
			setStudents(newStudents);
		} 
		return canAddStudent;
	}

	public boolean dropStudent(Student student) {
		boolean canDropStudent = false;
		int dropIndex = 0;
		if (students.length > 0) {
			String studentToDrop = student.toString();
			for(int i = 0; i < students.length; i++) {
				String currentStudent = students[i].toString();
				if (studentToDrop.equals(currentStudent)) {
					canDropStudent = true;
					dropIndex = i;
					break;
				}
			}
		}
		if (canDropStudent) {
			Student[] newStudents = new Student[students.length - 1];
			for (int i = 0; i < newStudents.length; i++) {
				if (i != dropIndex) {
					newStudents[i] = students[i];
				}
			}
			setStudents(newStudents);
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
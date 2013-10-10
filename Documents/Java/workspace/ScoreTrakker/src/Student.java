
public class Student implements Comparable<Student> {
	private String name;
	private int score;
	
	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return name + " " + score;
	}

	@Override
	public int compareTo(Student s) {
		return name.compareTo(s.name);
	}
	
	
	
	
}

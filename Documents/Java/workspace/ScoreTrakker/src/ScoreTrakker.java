import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ScoreTrakker {
	private ArrayList<Student> students;

	public ScoreTrakker() {
		super();
	}

	void loadDataFromFile(String filename) throws FileNotFoundException {
		students = new ArrayList<Student>();
		FileReader reader = new FileReader(filename);
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String name = in.nextLine();
			int score = in.nextInt();
			if (in.hasNextLine())
				in.nextLine();
			Student s = new Student(name,score);
			students.add(s);
		}
		in.close();
	}

	void printInOrder() {
		Collections.sort(students);
		for (Student stu: students) {
			System.out.println(stu.toString());
		}
	}

	void processFile(String filename) throws FileNotFoundException{
		loadDataFromFile(filename);
		printInOrder();
	}

	public static void main(String[] args) throws FileNotFoundException {
		ScoreTrakker x = new ScoreTrakker();
		x.processFile("scores.txt");
	}

}

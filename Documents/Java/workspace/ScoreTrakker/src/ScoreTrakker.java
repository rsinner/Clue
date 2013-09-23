import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ScoreTrakker {
	private ArrayList<Student> students;
	private String[] files = {"scores.txt", "badscore.txt", "nofile.txt"};

	public ScoreTrakker() {
		super();
	}

	void loadDataFromFile(String filename) throws FileNotFoundException {
		students = new ArrayList<Student>();
		FileReader reader = new FileReader(filename);
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String name = "";
			String score_string = "";
			int score = 0;
			try {
				name = in.nextLine();
				score_string = in.nextLine();
				//if (in.hasNextLine())
				//in.nextLine();
				score = Integer.parseInt(score_string);
				Student s = new Student(name,score);
				students.add(s);
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format for " + name + " not a valid score: " + score_string + "\n");
			}
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
		for (String f : files) {
			try {
				loadDataFromFile(f);
				printInOrder();
			} catch (FileNotFoundException e) {
				System.out.println("Can't open file: " + f);
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		ScoreTrakker x = new ScoreTrakker();
		x.processFile("scores.txt");
	}

}

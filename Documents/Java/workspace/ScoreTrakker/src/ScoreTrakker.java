import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ScoreTrakker {
	private ArrayList<Student> students;
	private String[] files = {"scores.txt", "badscore.txt", "nofile.txt", "badname.txt"};

	public ScoreTrakker() {
		super();
	}

	void loadDataFromFile(String filename) throws Exception {
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
				if (name.indexOf(" ") == -1)
					System.out.println("'" + name + "' does not include a first and last name \n");
				else {
					score = Integer.parseInt(score_string);
					Student s = new Student(name,score);
					students.add(s);
				}
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

	void processFile(String filename) {
		for (String f : files) {
			try {
				loadDataFromFile(f);
				printInOrder();
			} catch (FileNotFoundException e) {
				System.out.println("Can't open file: " + f);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		ScoreTrakker x = new ScoreTrakker();
		x.processFile("scores.txt");
	}

}

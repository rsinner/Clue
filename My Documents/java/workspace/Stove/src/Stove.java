import java.util.ArrayList;

public class Stove {
	public final int BURNERS = 4;
	ArrayList<Burner> myBurners;

	public Stove() {
		myBurners = new ArrayList<Burner>();
		for (int i = 0; i < BURNERS; i++)
			myBurners.add(new Burner());
	}

	public void displayStove() {
		System.out.println("Stove -----------------");
		boolean hot = false;
		for (Burner burn : myBurners) {
			burn.displayStatus();
			if (burn.temp == Burner.Temperature.HOT)
				hot = true;
		}
		if (hot == true)
			System.out.println("RED LIGHT - HOT BURNER ALERT");		
	}

	public void timePassing(int min) {
		for (Burner burn : myBurners){
			for (int m = 0; m < min; m++)
				burn.updateTemperature();
		}
	}

	public void setBurners() {
		// Simulate pressing increase setting button 3 times
		myBurners.get(0).increaseSetting();
		myBurners.get(0).increaseSetting();
		myBurners.get(0).increaseSetting();
		// Simulate pressing increase setting button 3 times, then
		// decrease button 1 time
		myBurners.get(1).increaseSetting();
		myBurners.get(1).increaseSetting();
		myBurners.get(1).increaseSetting();
		myBurners.get(1).decreaseSetting();
		// Simulate pressing increase setting button 1 time
		myBurners.get(2).increaseSetting();
	}

	public static void main(String[] args) {
		Stove stove = new Stove();
		stove.displayStove();
		stove.setBurners();
		stove.timePassing(8);
		stove.displayStove();
	}

}

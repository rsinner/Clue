

public class Burner {
	public enum Temperature {HOT, WARM, COLD};
	Settings.Setting setting;
	Temperature temp;
	public int time;
	final int TIME_DURATION = 3;

	public Burner() {
		temp = Temperature.COLD;
		setting = Settings.Setting.OFF;
	}

	public void increaseSetting() {
		switch(setting) {
		case OFF: 
			setting = Settings.Setting.LOW;
			time = 0;
			break;
		case LOW: 
			setting = Settings.Setting.MEDIUM;
			time = 0;
			break;
		case MEDIUM: 
			setting = Settings.Setting.HIGH;
			time = 0;
			break;
		case HIGH:
			time = 0;
			break;
		}
	}

	public void decreaseSetting() {
		switch(setting) {
		case OFF: 
			time = 0;
			break;
		case LOW: 
			setting = Settings.Setting.OFF;
			time = 0;
			break;
		case MEDIUM: 
			setting = Settings.Setting.LOW;
			time = 0;
			break;
		case HIGH: 
			setting = Settings.Setting.MEDIUM;
			time = 0;
			break;
		}
	}

	public void updateTemperature() {
		time += 1;
		if (time >= TIME_DURATION) {
			if (setting == Settings.Setting.HIGH)
				temp = Temperature.HOT;
			else if (setting == Settings.Setting.MEDIUM || setting == Settings.Setting.LOW)
				temp = Temperature.WARM;
			else
				temp = Temperature.COLD;
		}
	}

	public Temperature getTemp() {
		return temp;
	}
	
	public void displayStatus() {
		System.out.println("Stove -----------------");
		String disp;
		if (setting == Settings.Setting.HIGH)
			disp = "HOT SURFACE! DON'T TOUCH";
		else if (setting == Settings.Setting.MEDIUM || setting == Settings.Setting.LOW)
			disp = "CAREFUL";
		else
			disp = "cooool";
		System.out.println("[" + setting + "]....." + disp);
	}

	
}

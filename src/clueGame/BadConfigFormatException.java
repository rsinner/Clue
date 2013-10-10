package clueGame;

public class BadConfigFormatException extends Exception{
	
	private String message;
	
	public BadConfigFormatException(String s) {
		message = s;
	}
	
	@Override
	public String toString() {
		return message;
	}

	

}

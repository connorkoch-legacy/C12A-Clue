package clueGame;

import java.io.FileNotFoundException;

public class BadConfigFormatException extends Exception {
	
	public BadConfigFormatException() throws FileNotFoundException{
		super("Bad Config");
	}
	
	public BadConfigFormatException(String str) throws FileNotFoundException{
		
	}

}

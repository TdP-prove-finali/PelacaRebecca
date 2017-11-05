package data_set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data_set {
	
	public static List<String> loadLines(String file){
		
		ArrayList<String> rows = new ArrayList<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line;
			while( ( line = reader.readLine() ) != null )
				rows.add(line);
			
		}
		catch(IOException e) {
			
			System.err.println("Detected and error while reading file: " + file);
			return null;
		}
		return rows;
	}
}

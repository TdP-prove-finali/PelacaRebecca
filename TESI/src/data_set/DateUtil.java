package data_set;

import java.time.LocalDate;

public class DateUtil {
	
	public static LocalDate getDate(String dateString){
		int day=0, month=0, year=0;
		
		try{
			if (dateString.length()==5){
				day = Integer.parseInt(dateString.substring(0, 1));
				month = Integer.parseInt(dateString.substring(1, 3));
				year = Integer.parseInt(dateString.substring(3,5))+2000;
			} 
			else if (dateString.length()==6){
				day = Integer.parseInt(dateString.substring(0, 2));
				month = Integer.parseInt(dateString.substring(2, 4));
				year = Integer.parseInt(dateString.substring(4,6))+2000;
			}
		} catch (NumberFormatException e){
			System.out.println(dateString);
		}
		
		if (year!=0 && month!=0 && day!=0)
			return LocalDate.of(year, month, day);
		else
			return null;
	}

}

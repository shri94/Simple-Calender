package hw4;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
	@Override
	public int compare(Event eve1, Event eve2) {
		// TODO Auto-generated method stub
		int startTime = eve1.getStartTime().compareTo(eve2.getStartTime());
		int endTime = eve2.getEndTime().compareTo(eve2.getEndTime());
		int title = eve1.getEventTitle().compareTo(eve2.getEventTitle());		
		int year = eve1.getYear() - eve2.getYear();
		int month = eve1.getMonth() - eve2.getMonth();
		int day = eve1.getDay() - eve2.getDay();
		
		if(year == 0){ 
			if(month == 0) { 
				if(day == 0)  { 
					if(startTime == 0) { 
						if(endTime == 0) { 
							return title;}
						else return endTime;}
					else return startTime; } 
				else return day; } 
			else return month; } 
		else return year;
		
	}
	
	

}

package shelterfinder.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeFunctions {
	private static final String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss"; 
	private SimpleDateFormat dateFormat;
	
	public TimeFunctions() {
		dateFormat = new SimpleDateFormat(FORMAT_PATTERN);
	}
	
	public Date formatDate(String dateString) {
		Date result = null;
		try {
			result = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String calculteDateDif(Date now, Date date) {
		long milliseconds = now.getTime() - date.getTime();
		long minutes = milliseconds / 1000 / 60;
		if (minutes < 60) {
			return minutes + " phút";
		}
		else {
			long hours = minutes / 60;
			long dminutes = minutes % 60;
			if (hours < 24) {
				return hours + " giờ, " + dminutes + " phút";
			}
			else {
				long days = hours / 24;
				long dhours = hours % 24;
				return days + " ngày, " + dhours + " giờ, " + dminutes + " phút";
			}
		}
		
	}
}
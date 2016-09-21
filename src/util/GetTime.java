package util;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GetTime {

	public static String getTime(){
		DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		TimeZone timeZoneChina = TimeZone.getTimeZone("America/New_York");
		dateFormatterChina.setTimeZone(timeZoneChina);
		Date curDate = new Date();
		return dateFormatterChina.format(curDate);
	}

  public static void main(String args[]) {
    System.out.println(getTime());
  }
}

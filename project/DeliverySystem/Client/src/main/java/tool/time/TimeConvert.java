package tool.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sissel on 2015/11/22.
 */
public class TimeConvert {

    public static Calendar convertDate(LocalDate localDate){
        if(localDate == null)
            return null;

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static LocalDate convertCalendar(Calendar calendar) {
    	int year = calendar.get(Calendar.YEAR);
    	int month = calendar.get(Calendar.MONTH);
    	int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
		return localDate;
	}

    public static String getDisplayDate(Calendar calendar){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // month is a stupid design
        return year + "-" + (month + 1) + "-" + day;
    }

    public static String getDisplayDate(LocalDate date){
        int year = date.getYear();
        int month = date.getMonth().getValue();
        int day = date.getDayOfMonth();

        return year + "-" + month + "-" + day;
    }
}

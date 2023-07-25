package org.freefly.dodaily.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Tool for all
 * @usage ISDate.getInstance().*
 *
 * special class to set date for this project.
 * it should be a singleton.
 * the date format should be: yyyy-MM-dd HH:mm:ss:SSS.
 *
 * @author freefly365
 * @date 2023-07-25
 */
public class ISDate {

    private static ISDate isDate = null;
    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    private final String DEFAULT_TIMEZONE = "Asia/Shanghai";
    private final Locale LOCALE = Locale.CHINESE;
    private TimeZone timeZone = null;
    private SimpleDateFormat sdFormat = null;


    private ISDate() {
        if (timeZone == null) {
            timeZone = TimeZone.getTimeZone(DEFAULT_TIMEZONE);
        }
        if (sdFormat == null) {
            sdFormat = new SimpleDateFormat(DATE_FORMAT, LOCALE);
        }
    }

    public static ISDate getInstance() {
        if (isDate == null) {
            isDate = new ISDate();
        }
        return isDate;
    }

    public Date getDate(){
        return Calendar.getInstance(timeZone).getTime();
    }

    public String getDateAsString() {
        return sdFormat.format(Calendar.getInstance(timeZone).getTime());
    }

    public Date parseDateFromString(String date) {
        try {
            return sdFormat.parse(date);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

}

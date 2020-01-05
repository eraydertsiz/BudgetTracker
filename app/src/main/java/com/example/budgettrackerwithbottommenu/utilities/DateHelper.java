package com.example.budgettrackerwithbottommenu.utilities;

import java.util.Calendar;

public class DateHelper {

    public static long getNowAsSeconds(){
        return System.currentTimeMillis() / 1000L;
    }

    public static Calendar convertSecondsToCalendar(long seconds){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(seconds);
        return cal;
    }

    public static long convertDatetimeToSeconds(int year, int month, int day, int hour, int minute, int second){
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.set(year, month, day, hour, minute, second);
        return convertCalendarToSeconds(calendarDate);
    }

    public static long convertCalendarToSeconds(Calendar cal){
        return (cal.getTimeInMillis() / 1000L);
    }

    public static boolean isCalendarValid(Calendar calendar){
        try{
            calendar.setLenient(false);
            calendar.getTimeInMillis();
            return true;
        }
        catch(Exception e) {
            calendar = null;
            return false;
        }
    }

}

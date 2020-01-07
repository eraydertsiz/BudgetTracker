package com.example.budgettrackerwithbottommenu.utilities;

import java.util.Calendar;

public class DateHelper {

    public static long getNowAsSeconds(){
        return System.currentTimeMillis() / 1000L;
    }

    public static long getTodayAsSeconds(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000L;
    }

    public static long getEndOfTodayAsSeconds(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000L + 86399;
    }

    public static Calendar convertSecondsToCalendar(long seconds){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(seconds * 1000);
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

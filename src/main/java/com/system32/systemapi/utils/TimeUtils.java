package com.system32.systemapi.utils;

public class TimeUtils {
    public static long getTimeInMillis(int day, int hour, int minute, int second) {
        long currentTime = 0L;
        long daylong = (day * 86400000L);
        long hourLong = (hour * 3600000L);
        long minuteLong = (minute * 60000L);
        long secondLong = (second * 1000L);

        return currentTime + daylong + hourLong + minuteLong + secondLong;
    }

    public static String getTimeFormat(long maxTime, long minTime) {
        long millis = maxTime - minTime;
        int day;
        int hour;
        int minute;
        int second;
        long timeleft;

        day = (int) (millis / 86400000L);
        timeleft = millis % 86400000L;

        hour = (int) (timeleft / 3600000L);
        timeleft %= 3600000L;

        minute = (int) (timeleft / 60000L);
        timeleft %= 60000L;

        second = (int) timeleft / 1000;

        String message = "";
        if (day > 0) message = message + day + " Day" + ((day > 1) ? "s" : "") + " ";

        if (hour > 0) message = message + hour + " Hour" + ((hour > 1) ? "s" : "") + " ";

        if (minute > 0) message = message + minute + " Minute" + ((minute > 1) ? "s" : "") + " ";

        if (second > 0) message = message + second + " Second" + ((second > 1) ? "s" : "") + " ";

        return message;
    }

    public static String getTimeBetweenFormat(long maxTime, long minTime) {
        long millis = maxTime - minTime;
        int day;
        int hour;
        int minute;
        int second;
        long timeleft;

        day = (int) (millis / 86400000L);
        timeleft = millis % 86400000L;

        hour = (int) (timeleft / 3600000L);
        timeleft %= 3600000L;

        minute = (int) (timeleft / 60000L);
        timeleft %= 60000L;

        second = (int) timeleft / 1000;

        String message = "";
        if (day > 0) message = message + day + "d ";

        if (hour > 0) message = message + hour + "h ";

        if (minute > 0) message = message + minute + "m ";

        if (second > 0) message = message + second + "s ";

        return message;
    }
}

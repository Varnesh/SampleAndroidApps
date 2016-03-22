package fb.robo.com.gcmtestapplication;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by sohan on 1/7/15.
 */
public class TimeStampUtils {
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    public static String getTimeInHHMM(Date date) {
        // formatting time to have AM/PM text using 'a' format
        String formattedTime = "";
        try {
            String strDateFormat = "hh:mm";
            formattedTime = DateFormat.format(strDateFormat, date).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedTime;
    }

    public static String getTimeInAMOrPM(Date date) {
        // formatting time to have AM/PM text using 'a' format
        String formattedTime = "";
        try {
            String strDateFormat = "hh:mm aaa";
            formattedTime = DateFormat.format(strDateFormat, date).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedTime;
    }
}

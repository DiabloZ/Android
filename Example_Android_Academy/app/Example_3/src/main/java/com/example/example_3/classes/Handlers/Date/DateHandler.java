package com.example.example_3.classes.Handlers.Date;

import com.example.example_3.classes.Handlers.FitText.FitTextPublisher;
import com.example.example_3.classes.Handlers.FitText.Fitable;
import com.example.example_3.classes.Handlers.Orientation.Orientatinable;
import com.example.example_3.classes.Handlers.Orientation.OrientationPublisher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHandler implements Orientatinable, Fitable {
    private OrientationPublisher orientationPublisher = OrientationPublisher.getInstance();
    private FitTextPublisher fitTextPublisher = FitTextPublisher.getInstance();

    private final String OLD_DATE_FORMAT = "MMM dd, yyyy h:mm:ss a";
    private final String NEW_DATE_FORMAT = "d MMM yyyy HH:mm:ss";
    private String newLine = "";

    private String completedLine;
    private Date dateNow = new Date(System.currentTimeMillis());
    private Date date = new Date();

    private long days = 0;
    private long hours = 0;
    private long minutes = 0;
    private long seconds = 0;
    private long months = 0;
    private long years = 0;

    private boolean fitTextOnDisplay;
    private boolean orientationLandscape;


    public String getCompletedLine() {
        return completedLine;
    }

    public DateHandler(String dateParse) {
        this.completedLine = getNewDataFormat(dateParse);
    }

    @Override
    public void updateOrientation(boolean orientationLandscape) {
        this.orientationLandscape = orientationLandscape;
    }

    @Override
    public void updateFitText(boolean fitTextOnDisplay) {
        this.fitTextOnDisplay = fitTextOnDisplay;
    }

    private String getNewDataFormat(String dateParse) {
        orientationPublisher.subscribe(this);
        fitTextPublisher.subscribe(this);

        newLine = !fitTextOnDisplay ? "\n" : "";

        SimpleDateFormat old_sdf = new SimpleDateFormat(OLD_DATE_FORMAT, Locale.ENGLISH);
        SimpleDateFormat new_sdf = new SimpleDateFormat(NEW_DATE_FORMAT);

        try {
            date = old_sdf.parse(dateParse);
            long raw = dateNow.getTime()- date.getTime();

            seconds = raw / 1000;
            minutes = seconds / 60;
            hours = minutes / 60;
            days = hours / 24;
            months = days / 30;
            years = months / 12;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (years > 0) {
            return years + " лет назад. " + newLine + new_sdf.format(date);
        } else if (months > 0) {
            return months + " месяца назад. " + newLine + new_sdf.format(date);
        } else if (days > 0) {
            return days + " дней назад. " + newLine + new_sdf.format(date);
        } else if (hours > 0){
            return hours + " часов назад. " + newLine + new_sdf.format(date);
        }else if (minutes > 0){
            return minutes + " минут назад. " + newLine + new_sdf.format(date);
        }else if (seconds > 0){
            return seconds + " секунд назад. " + newLine + new_sdf.format(date);
        }
        return "Только что.";
    }

}

package com.guzcode.healthapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class anmPatient {
    int id;
    String name;
    String lName;
    String BornDate;
    String cc;
    String sex;
    String email;
    int hemLevel;
    String state;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String anmCalc(int hemLevel, String sex, String born) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        LocalDate date = LocalDate.parse(born, formatter);
        LocalDate now = LocalDate.parse(currentDate, formatter);
        int years = Period.between(date, now).getYears();
        if (years < 1) {
            int months = Period.between(date, now).getMonths();
            if (months <= 6 && hemLevel < 10 && months > 1) {
                return "Anemia";
            }
            if (months < 1 && hemLevel < 13) {
                return "Anemia";
            }
            if (hemLevel < 11) {
                return "Anemia";
            }
        } else {
            if (years <= 5 && hemLevel < 11) {
                return "Anemia";
            }
            if (years <= 10 && hemLevel < 12) {
                return "Anemia";
            }
            if (sex == "male" && hemLevel < 14) {
                return "Anemia";
            }
            if (sex == "female" && hemLevel < 12) {
                return "Anemia";
            }

        }
        return "healthy";

    }
}
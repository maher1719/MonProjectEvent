package com.example.miniprojetevents.database;

import android.util.Log;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {

    static DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS");

    @TypeConverter
    public static Date toDate(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                Log.e("date", e.getMessage());
            }
            return null;
        } else {
            return null;
        }
    }


    @TypeConverter
    public static String dateToTime(Date value) {
        if (value != null) {
            return df.format(value);
        } else {
            return null;
        }
    }
}

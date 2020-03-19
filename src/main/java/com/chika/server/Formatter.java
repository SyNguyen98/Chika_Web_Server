package com.chika.server;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Formatter {

    public static String formatTime(Long time) {
        return new SimpleDateFormat("HH:mm dd/MM/yyyy").format(time);
    }

    public static String formatTime(Timestamp time) {
        return new SimpleDateFormat("HH:mm dd/MM/yyyy").format(time);
    }

    public static String formatDay(Timestamp time) {
        return new SimpleDateFormat("dd/MM/yyyy").format(time);
    }
}

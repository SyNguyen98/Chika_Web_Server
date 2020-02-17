package com.chika.server;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Formatter {

    public static String formatTimeFull(Long time) {
        return new SimpleDateFormat("HH:mm dd/MM/yyyy").format(time);
    }

    public static String formatTimeFull(Timestamp time) {
        return new SimpleDateFormat("HH:mm dd/MM/yyyy").format(time);
    }

    public static String formatTimeDay(Timestamp time) {
        return new SimpleDateFormat("dd/MM/yyyy").format(time);
    }
}

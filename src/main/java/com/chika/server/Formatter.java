package com.chika.server;

import java.text.SimpleDateFormat;

public class Formatter {

    public static String formatTime(Long time) {
        return new SimpleDateFormat("HH:mm dd/MM/yyyy").format(time);
    }
}

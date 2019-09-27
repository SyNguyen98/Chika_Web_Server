package com.chika.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@SpringBootApplication
public class ChikaWebServerApplication {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                                                                        .withLocale( Locale.UK )
                                                                        .withZone( ZoneId.systemDefault() );

    public static void main(String[] args) {
        SpringApplication.run(ChikaWebServerApplication.class, args);
    }

}

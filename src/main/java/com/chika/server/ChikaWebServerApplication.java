package com.chika.server;

import com.chika.server.models.device.ModuleIr;
import com.chika.server.services.ModuleIrService;
import com.chika.server.services.MqttService;
import com.chika.server.services.RemoteIrService;
import com.chika.server.services.implement.MqttServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static ModuleIrService moduleIrService;

    public ChikaWebServerApplication(ModuleIrService moduleIrService) {
        ChikaWebServerApplication.moduleIrService = moduleIrService;
    }

    public static MqttService mqttService = new MqttServiceImpl();

    public static void main(String[] args) {
        SpringApplication.run(ChikaWebServerApplication.class, args);

        for (ModuleIr moduleIr : moduleIrService.getAll()) {
            mqttService.subscribe(moduleIr.getId() + "/control");
        }
    }
}

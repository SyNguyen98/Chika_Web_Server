package com.chika.server;

import com.chika.server.models.script.Script;
import com.chika.server.repositories.house.ScriptRepository;
import com.chika.server.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableScheduling
public class ChikaWebServerApplication {

    private final ScriptRepository scriptRepository;

    private final ScheduleService scheduleService;

    public ChikaWebServerApplication(ScriptRepository scriptRepository, ScheduleService scheduleService) {
        this.scriptRepository = scriptRepository;
        this.scheduleService = scheduleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChikaWebServerApplication.class, args);
    }

    @PostConstruct
    void initializeSchedule() {
        List<Script> scripts = scriptRepository.findAll();
        if (!scripts.isEmpty()) {
            scripts.forEach(script -> {
                scheduleService.initialize(script);
                System.out.println(script.getName() + " started");
            });
        }
    }
}

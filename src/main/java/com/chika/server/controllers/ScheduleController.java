package com.chika.server.controllers;

import com.chika.server.models.script.Script;
import com.chika.server.models.script.ScriptDevice;
import com.chika.server.repositories.house.ScriptDeviceRepository;
import com.chika.server.repositories.house.ScriptRepository;
import com.chika.server.services.ScheduleService;
import org.springframework.web.bind.annotation.*;

/**
 * To receive Schedule requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScriptRepository scriptRepository;

    private final ScriptDeviceRepository scriptDeviceRepository;

    private final ScheduleService scheduleService;

    public ScheduleController(ScriptRepository scriptRepository, ScriptDeviceRepository scriptDeviceRepository, ScheduleService scheduleService) {
        this.scriptRepository = scriptRepository;
        this.scriptDeviceRepository = scriptDeviceRepository;
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public void changeSchedule(@RequestBody Script script) {
        Script newScript = scriptRepository.save(new Script(script.getName(), script.getTime()));
        script.getDevices().forEach(scriptDevice ->
                scriptDeviceRepository.save(new ScriptDevice(scriptDevice.getDeviceId(), newScript.getId())));
        scheduleService.initialize(script);
    }

    @PutMapping("/cancel")
    public void cancelSchedule() {
        scheduleService.cancelAll();
    }
}

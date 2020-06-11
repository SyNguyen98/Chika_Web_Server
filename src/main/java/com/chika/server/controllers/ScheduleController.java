package com.chika.server.controllers;

import com.chika.server.models.house.Script;
import com.chika.server.models.house.ScriptDevice;
import com.chika.server.repositories.house.ScriptDeviceRepository;
import com.chika.server.repositories.house.ScriptRepository;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * To receive Schedule requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-06-2020
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
    public ResponseEntity<?> createSchedule(@CurrentUser UserPrincipal currentUser, @RequestBody Script script) {
        Script newScript = scriptRepository.save(new Script(
                script.getLogo(), script.getName(), script.getTime(), script.getDays(), currentUser.getId()));
        script.getDevices().forEach(scriptDevice ->
                scriptDeviceRepository.save(new ScriptDevice(
                        scriptDevice.getDeviceId(), scriptDevice.getName(), scriptDevice.getType(),
                        scriptDevice.getTopic(), scriptDevice.getState(), scriptDevice.getSwitchButton(),
                        newScript.getId())));
        scheduleService.initialize(script);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/cancel")
    public void cancelSchedule() {
        scheduleService.cancelAll();
    }
}

package com.chika.server.controllers;

import com.chika.server.models.house.Script;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.ScheduleService;
import com.chika.server.services.ScriptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Schedule requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 12-06-2020
 */
@RestController
@RequestMapping("/script")
public class ScriptController {

    private final ScriptService scriptService;
    private final ScheduleService scheduleService;

    public ScriptController(ScriptService scriptService, ScheduleService scheduleService) {
        this.scriptService = scriptService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<Script> getAllScriptByUserId(@CurrentUser UserPrincipal currentUser) {
        return scriptService.getAllByUserId(currentUser.getId());
    }

    @PostMapping
    public ResponseEntity<?> addScript(@CurrentUser UserPrincipal currentUser, @RequestBody Script script) {
        scheduleService.initialize(script);
        script.setUserId(currentUser.getId());
        return ResponseEntity.ok(scriptService.save(script));
    }

    @PutMapping("/cancel")
    public void cancelSchedule() {
        scheduleService.cancelAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScriptById(@PathVariable String id) {
        scriptService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Script has been deleted"));
    }
}

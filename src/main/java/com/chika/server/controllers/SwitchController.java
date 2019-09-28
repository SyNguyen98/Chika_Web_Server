package com.chika.server.controllers;

import com.chika.server.models.house.Switch;
import com.chika.server.services.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 27-09-2019
 */
@RestController
@RequestMapping("/switch")
public class SwitchController {

    @Autowired
    private SwitchService switchService;

//    @GetMapping("/{userId}")
//    public List<Switch> getSwitchesByUserId(@PathVariable Long userId) {
//        return switchService.getAllSwitchesByUserId(userId);
//    }

    @GetMapping("/{userId}")
    public int getSwitchesByUserId(@PathVariable Long userId) {
        return 1;
    }

    @PostMapping("/{userId}")
    public Switch saveSwitch(@PathVariable Long userId) {
        Switch _switch = new Switch(userId);
        return switchService.saveSwitch(_switch);
    }

    @DeleteMapping("/{id}")
    public void deleteSwitch(@PathVariable String id) {
        switchService.deleteSwitch(id);
    }
}

package com.chika.server.controllers;

import com.chika.server.models.house.Switch;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
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

    @GetMapping
    public List<Switch> getSwitchesByUserId(@CurrentUser UserPrincipal currentUser) {
        return switchService.getAllSwitchesByUserId(currentUser.getId());
    }

    @PostMapping
    public Switch saveSwitch(@CurrentUser UserPrincipal currentUser) {
        return switchService.saveSwitch(new Switch(currentUser.getId()));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteSwitch(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (switchService.isSwitchOwner(id, currentUser.getId())) {
            switchService.deleteSwitch(id);
            return true;
        }
        return false;
    }
}

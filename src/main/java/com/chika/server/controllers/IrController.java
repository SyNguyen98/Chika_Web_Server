package com.chika.server.controllers;

import com.chika.server.models.house.IR;
import com.chika.server.services.IrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ir")
public class IrController {

    @Autowired
    private IrService irService;

    @GetMapping("/{userId}")
    public List<IR> getAllIrByUserId(@PathVariable Long userId) {
        return irService.getIrByUserId(userId);
    }

    @PostMapping("/{userId}/{quantity}")
    public List<IR> saveAllIr(@PathVariable Long userId, @PathVariable int quantity) {
        return irService.saveListIr(userId, quantity);
    }

    @PutMapping("/{id}/{value}")
    public IR updateIr(@PathVariable String id, @PathVariable String value) {
        return irService.updateIr(id, value);
    }

    @DeleteMapping("/{userId}")
    public void deleteAllIrByUserId(@PathVariable Long userId) {
        irService.deleteIrByUserId(userId);
    }
}

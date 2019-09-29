package com.chika.server.controllers;

import com.chika.server.models.house.IR;
import com.chika.server.payload.requests.IrRequest;
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

    @PostMapping
    public List<IR> saveAllIr(@RequestBody IrRequest irRequest) {
        return irService.saveListIr(irRequest.getUserId(), irRequest.getQuantity());
    }

    @PutMapping
    public IR updateIr(@RequestBody IR ir) {
        return irService.updateIr(ir.getId(), ir.getValue());
    }

    @DeleteMapping("/{userId}")
    public void deleteAllIrByUserId(@PathVariable Long userId) {
        irService.deleteIrByUserId(userId);
    }
}

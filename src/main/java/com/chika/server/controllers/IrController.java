package com.chika.server.controllers;

import com.chika.server.models.house.IrValue;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.IrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ir")
public class IrController {

    @Autowired
    private IrValueService irValueService;

    @GetMapping("/{remoteId}")
    public List<IrValue> getAllByRemoteIrId(@PathVariable String remoteId) {
        return irValueService.getAllByRemoteIrId(remoteId);
    }

    @PostMapping("/{remoteId}")
    public List<IrValue> saveAll(@PathVariable String remoteId, @RequestParam int quantity) {
        return irValueService.saveList(remoteId, quantity);
    }

    @PutMapping
    public IrValue updateValue(@RequestBody IrValue irValue) {
        return irValueService.updateValue(irValue.getId(), irValue.getValue());
    }
}

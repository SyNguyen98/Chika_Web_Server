package com.chika.server.controllers;

import com.chika.server.models.house.IrValue;
import com.chika.server.services.IrValueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Ir Value requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/irvalue")
public class IrValueController {

    private final IrValueService irValueService;

    public IrValueController(IrValueService irValueService) {
        this.irValueService = irValueService;
    }

    @GetMapping("/{remoteId}")
    public List<IrValue> getAllByRemoteIrId(@PathVariable String remoteId) {
        return irValueService.getAllByRemoteIrId(remoteId);
    }

    @PutMapping
    public IrValue updateValue(@RequestBody IrValue irValue) {
        return irValueService.updateValue(irValue.getId(), irValue.getValue());
    }
}

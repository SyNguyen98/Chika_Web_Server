package com.chika.server.controllers;

import com.chika.server.models.house.IrData;
import com.chika.server.models.house.IrValue;
import com.chika.server.payload.responses.house.IrValueResponse;
import com.chika.server.services.IrService;
import org.springframework.web.bind.annotation.*;

/**
 * To receive Ir Value requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-05-2019
 */
@RestController
@RequestMapping("/ir")
public class IrController {

    private final IrService irService;

    public IrController(IrService irService) {
        this.irService = irService;
    }

    @GetMapping("value")
    public IrValueResponse getAllIrValueByDeviceAndProtocol(@RequestParam("device") String device,
                                                            @RequestParam("protocol") String protocol) {
        return new IrValueResponse(irService.getByDeviceAndProtocol(device.toUpperCase(), protocol.toUpperCase()));
    }

    @GetMapping("value/{id}")
    public IrValueResponse getIrValueById(@PathVariable String id) {
        return new IrValueResponse(irService.getById(id));
    }

    @PostMapping("value")
    public IrValue saveIrValue(@RequestBody IrValue irValue) {
        return irService.saveValue(irValue);
    }

    @PostMapping("data")
    public IrData saveIrData(@RequestBody IrData irData) {
        return irService.saveData(irData);
    }
}

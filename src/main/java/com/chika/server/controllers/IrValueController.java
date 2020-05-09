package com.chika.server.controllers;

import com.chika.server.models.house.IrValue;
import com.chika.server.payload.responses.house.IrValueResponse;
import com.chika.server.services.IrValueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Ir Value requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 09-05-2019
 */
@RestController
@RequestMapping("/ir-value")
public class IrValueController {

    private final IrValueService irValueService;

    public IrValueController(IrValueService irValueService) {
        this.irValueService = irValueService;
    }

    @GetMapping("batch")
    public List<IrValueResponse> getAllIrValueByDeviceAndProtocol(@RequestParam("device") String device,
                                                                  @RequestParam("protocol") String protocol) {
        return irValueService.getAllByDeviceAndProtocol(device.toUpperCase(), protocol.toUpperCase()).stream()
                .map(IrValueResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public IrValueResponse getIrValueById(@PathVariable String id) {
        return new IrValueResponse(irValueService.getById(id));
    }

    @GetMapping
    public IrValueResponse getIrValueByDeviceAndProtocolAndFunction(@RequestParam("device") String device,
                                                                    @RequestParam("protocol") String protocol,
                                                                    @RequestParam("function") String function) {
        return new IrValueResponse(irValueService.getByDeviceAndProtocolAndFunction(
                device.toUpperCase(), protocol.toUpperCase(), function.toUpperCase()));
    }

    @PostMapping
    public IrValue saveIrValue(@RequestBody IrValue irValue) {
        return irValueService.save(irValue);
    }
}

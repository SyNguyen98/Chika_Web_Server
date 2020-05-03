package com.chika.server.controllers;

import com.chika.server.payload.responses.house.HistoryResponse;
import com.chika.server.services.DeviceHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("device-history")
public class DeviceHistoryController {

    private final DeviceHistoryService deviceHistoryService;

    public DeviceHistoryController(DeviceHistoryService deviceHistoryService) {
        this.deviceHistoryService = deviceHistoryService;
    }

    @GetMapping("{deviceId}")
    public List<HistoryResponse> getDeviceHistories(@PathVariable String deviceId,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size) {
        return deviceHistoryService.getListByDeviceId(deviceId, page, size).stream()
                .map(HistoryResponse::new)
                .collect(Collectors.toList());
    }
}

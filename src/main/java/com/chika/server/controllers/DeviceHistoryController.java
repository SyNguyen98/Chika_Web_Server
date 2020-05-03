package com.chika.server.controllers;

import com.chika.server.models.histories.DeviceHistory;
import com.chika.server.services.DeviceHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("device-history")
public class DeviceHistoryController {

    private final DeviceHistoryService deviceHistoryService;

    public DeviceHistoryController(DeviceHistoryService deviceHistoryService) {
        this.deviceHistoryService = deviceHistoryService;
    }

    @GetMapping("{deviceId}")
    public List<DeviceHistory> getDeviceHistories(@PathVariable String deviceId,
                                                  @RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        return deviceHistoryService.getListByDeviceId(deviceId, page, size);
    }
}

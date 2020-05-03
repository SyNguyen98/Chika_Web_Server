package com.chika.server.services;

import com.chika.server.models.histories.DeviceHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceHistoryService {

    List<DeviceHistory> getListByDeviceId(String deviceId, int page, int size);

    DeviceHistory save(DeviceHistory deviceHistory);
}

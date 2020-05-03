package com.chika.server.services.implement;

import com.chika.server.models.histories.DeviceHistory;
import com.chika.server.repositories.histories.DeviceHistoryRepository;
import com.chika.server.services.DeviceHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceHistoryServiceImpl implements DeviceHistoryService {

    private final DeviceHistoryRepository deviceHistoryRepository;

    public DeviceHistoryServiceImpl(DeviceHistoryRepository deviceHistoryRepository) {
        this.deviceHistoryRepository = deviceHistoryRepository;
    }

    @Override
    public List<DeviceHistory> getListByDeviceId(String deviceId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return deviceHistoryRepository.findAllByDeviceId(deviceId, pageable);
    }

    @Override
    public DeviceHistory save(DeviceHistory deviceHistory) {
        return deviceHistoryRepository.save(deviceHistory);
    }
}

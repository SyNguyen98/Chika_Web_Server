package com.chika.server.payload.responses;

import com.chika.server.Formatter;
import com.chika.server.models.house.DeviceHistory;
import lombok.Data;

@Data
public class DeviceHistoryResponse {

    public int state;

    public String time;

    public DeviceHistoryResponse(DeviceHistory deviceHistory) {
        this.state = deviceHistory.getState();
        this.time = Formatter.formatTime(deviceHistory.getCreatedAt().getTime());
    }
}
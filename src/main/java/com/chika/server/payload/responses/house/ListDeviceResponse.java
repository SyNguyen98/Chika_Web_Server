package com.chika.server.payload.responses.house;

import com.chika.server.models.house.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListDeviceResponse {

    private List<Device> sensors;

    private List<Device> switches;

    private List<Device> remoteIr;
}

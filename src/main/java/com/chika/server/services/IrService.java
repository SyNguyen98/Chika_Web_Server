package com.chika.server.services;

import com.chika.server.models.house.IrData;
import com.chika.server.models.house.IrValue;
import org.springframework.stereotype.Service;

@Service
public interface IrService {

    IrValue getByDeviceAndProtocol(String device, String protocol);

    IrValue getById(String id);

    IrValue saveValue(IrValue irValue);

    IrData saveData(IrData irData);
}

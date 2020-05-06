package com.chika.server.services;

import com.chika.server.models.house.IrValue;
import org.springframework.stereotype.Service;

@Service
public interface IrValueService {

    IrValue getById(String id);

    IrValue getByDeviceAndProtocolAndFunction(String device, String protocol, String function);

    IrValue save(IrValue irValue);
}

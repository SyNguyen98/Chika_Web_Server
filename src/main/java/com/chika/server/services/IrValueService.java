package com.chika.server.services;

import com.chika.server.models.house.IrValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IrValueService {

    List<IrValue> getAllByDeviceAndProtocol(String device, String protocol);

    IrValue getById(String id);

    IrValue getByDeviceAndProtocolAndFunction(String device, String protocol, String function);

    IrValue save(IrValue irValue);
}

package com.chika.server.services;

import com.chika.server.models.house.IrValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IrValueService {

    List<IrValue> getAllByRemoteIrId(String remoteIrId);

    IrValue getById(String id);

    List<IrValue> saveList(String remoteIrId, int quantity);

    IrValue updateValue(String id, String value);

    void deleteAllByRemoteIrId(String remoteIrId);
}

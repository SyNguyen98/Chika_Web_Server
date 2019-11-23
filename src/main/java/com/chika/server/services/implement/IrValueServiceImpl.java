package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.IrValue;
import com.chika.server.repositories.house.IrValueRepository;
import com.chika.server.services.IrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Manipulating data in the Ir table
 * @author Sy Nguyen
 * @version 1.0
 * @since 23-11-2019
 */
@Service
public class IrValueServiceImpl implements IrValueService {

    @Autowired
    private IrValueRepository irValueRepository;

    @Override
    @Transactional
    public List<IrValue> getAllByRemoteIrId(String remoteIrId) {
        return irValueRepository.findAllByRemoteId(remoteIrId);
    }

    @Override
    public IrValue getById(String id) {
        return irValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IR", "id", id));
    }

    @Override
    @Transactional
    public List<IrValue> saveList(String remoteIrId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            IrValue irValue = new IrValue(String.valueOf(i), "", remoteIrId);
            irValueRepository.save(irValue);
        }
        return getAllByRemoteIrId(remoteIrId);
    }

    @Override
    public IrValue updateValue(String id, String value) {
        IrValue irValue = getById(id);
        irValue.setValue(value);
        return irValueRepository.save(irValue);
    }

    @Override
    @Transactional
    public void deleteAllByRemoteIrId(String remoteIrId) {
        irValueRepository.deleteAllByRemoteId(remoteIrId);
    }
}

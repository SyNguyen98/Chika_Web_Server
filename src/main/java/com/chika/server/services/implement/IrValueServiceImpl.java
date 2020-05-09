package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.IrValue;
import com.chika.server.repositories.house.IrValueRepository;
import com.chika.server.services.IrValueService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD functions for Ir Value
 * @author Sy Nguyen
 * @version 1.0
 * @since 09-05-2020
 */
@Service
public class IrValueServiceImpl implements IrValueService {

    private final IrValueRepository irValueRepository;

    public IrValueServiceImpl(IrValueRepository irValueRepository) {
        this.irValueRepository = irValueRepository;
    }

    @Override
    @Transactional
    public List<IrValue> getAllByDeviceAndProtocol(String device, String protocol) {
        return irValueRepository.findAllByDeviceAndProtocol(device, protocol);
    }

    @Override
    public IrValue getById(String id) {
        return irValueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ir Value", "id", id));
    }

    @Override
    public IrValue getByDeviceAndProtocolAndFunction(String device, String protocol, String function) {
        return irValueRepository.findByDeviceAndProtocolAndFunction(device, protocol, function)
                .orElseThrow(() -> new ResourceNotFoundException("Ir Value", "device", device));
    }

    @Override
    public IrValue save(IrValue irValue) {
        return irValueRepository.save(irValue);
    }
}

package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.IrData;
import com.chika.server.models.house.IrValue;
import com.chika.server.repositories.house.IrDataRepository;
import com.chika.server.repositories.house.IrValueRepository;
import com.chika.server.services.IrService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD functions for Ir Value
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-05-2020
 */
@Service
public class IrServiceImpl implements IrService {

    private final IrValueRepository irValueRepository;

    private final IrDataRepository irDataRepository;

    public IrServiceImpl(IrValueRepository irValueRepository, IrDataRepository irDataRepository) {
        this.irValueRepository = irValueRepository;
        this.irDataRepository = irDataRepository;
    }

    @Override
    @Transactional
    public IrValue getByDeviceAndProtocol(String device, String protocol) {
        return irValueRepository.findByDeviceAndProtocol(device, protocol);
    }

    @Override
    public IrValue getById(String id) {
        return irValueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ir Value", "id", id));
    }

    @Override
    public IrValue saveValue(IrValue irValue) {
        return irValueRepository.save(irValue);
    }

    @Override
    @Transactional
    public IrData saveData(IrData irData) {
        return irDataRepository.save(irData);
    }
}

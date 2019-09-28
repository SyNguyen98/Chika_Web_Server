package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.IR;
import com.chika.server.repositories.IrRepository;
import com.chika.server.services.IrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrServiceImpl implements IrService {

    @Autowired
    private IrRepository irRepository;

    @Override
    public List<IR> getIrByUserId(Long userId) {
        return irRepository.findAllByUserId(userId);
    }

    @Override
    public List<IR> saveListIr(Long userId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            IR ir = new IR(userId);
            irRepository.save(ir);
        }
        return getIrByUserId(userId);
    }

    @Override
    public IR saveIr(Long userId, String value) {
        return null;
    }

    @Override
    public IR updateIr(String id, String value) {
        IR ir = irRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IR", "id", id));
        ir.setValue(value);
        return irRepository.save(ir);
    }

    @Override
    public void deleteIrByUserId(Long userId) {
        irRepository.deleteAllByUserId(userId);
    }
}

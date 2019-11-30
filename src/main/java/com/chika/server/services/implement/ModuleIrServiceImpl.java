package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.device.ModuleIr;
import com.chika.server.repositories.device.ModuleIrRepository;
import com.chika.server.services.ModuleIrService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleIrServiceImpl implements ModuleIrService {

    private final ModuleIrRepository moduleIrRepository;

    public ModuleIrServiceImpl(ModuleIrRepository moduleIrRepository) {
        this.moduleIrRepository = moduleIrRepository;
    }

    @Override
    public List<ModuleIr> getAll() {
        return moduleIrRepository.findAll();
    }

    @Override
    public ModuleIr getById(String id) {
        return moduleIrRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module Ir", "id", id));
    }

    @Override
    public ModuleIr save(ModuleIr moduleIr) {
        return moduleIrRepository.save(moduleIr);
    }

    @Override
    public ModuleIr updateUser(String id, Long userId) {
        ModuleIr moduleIr = getById(id);
        moduleIr.setUserId(userId);
        return moduleIrRepository.save(moduleIr);
    }

    @Override
    public void deleteById(String id) {
        moduleIrRepository.deleteById(id);
    }
}

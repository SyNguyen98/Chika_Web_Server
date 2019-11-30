package com.chika.server.services;

import com.chika.server.models.device.ModuleIr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleIrService {

    List<ModuleIr> getAll();

    ModuleIr getById(String id);

    ModuleIr save(ModuleIr moduleIr);

    ModuleIr updateUser(String id, Long userId);

    void deleteById(String id);
}

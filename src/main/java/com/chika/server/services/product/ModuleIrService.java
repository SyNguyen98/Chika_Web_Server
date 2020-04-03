package com.chika.server.services.product;

import com.chika.server.models.product.ModuleIr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleIrService {

    List<ModuleIr> getAll();

    List<ModuleIr> getAllByUserId(Long userId);

    ModuleIr getById(String id);

    ModuleIr save(ModuleIr moduleIr);

    ModuleIr updateName(String id, String name);

    ModuleIr updateUser(String id, Long userId);

    void deleteById(String id);

    long countAll();

    long countByUserId(Long userId);

    Boolean hasOwner(String id);
}

package com.chika.server.services;

import com.chika.server.models.product.Switch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SwitchService {

    List<Switch> getAll();

    Switch getById(String id);

    Switch save(Switch _switch);

    Switch updateName(String id, String name);

    Switch updateUser(String id, Long userId);

    void deleteById(String id);

    Boolean isOwner(String id, Long userId);
}

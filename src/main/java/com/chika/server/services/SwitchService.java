package com.chika.server.services;

import com.chika.server.models.device.Switch;
import org.springframework.stereotype.Service;

@Service
public interface SwitchService {

    Switch getById(String id);

    Switch save(Switch _switch);

    void delete(String id);
}

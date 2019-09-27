package com.chika.server.services;

import com.chika.server.models.house.Switch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SwitchService {

    List<Switch> getAllSwitchesByUserId(Long userId);

    Switch saveSwitch(Switch _switch);

    void deleteSwitch(String id);
}

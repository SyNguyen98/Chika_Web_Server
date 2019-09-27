package com.chika.server.services.implement;

import com.chika.server.models.house.Switch;
import com.chika.server.repositories.SwitchRepository;
import com.chika.server.services.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 27-09-2019
 */
@Service
public class SwitchServiceImpl implements SwitchService {

    @Autowired
    private SwitchRepository switchRepository;

    @Override
    @Transactional
    public List<Switch> getAllSwitchesByUserId(Long userId) {
        return switchRepository.findAllByUserId(userId);
    }

    @Override
    public Switch saveSwitch(Switch _switch) {
        return switchRepository.save(_switch);
    }

    @Override
    public void deleteSwitch(String id) {
        switchRepository.deleteById(id);
    }
}

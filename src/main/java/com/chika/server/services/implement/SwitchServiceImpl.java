package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.device.Switch;
import com.chika.server.repositories.device.SwitchRepository;
import com.chika.server.services.SwitchService;
import org.springframework.stereotype.Service;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 30-11-2019
 */
@Service
public class SwitchServiceImpl implements SwitchService {

    private final SwitchRepository switchRepository;

    public SwitchServiceImpl(SwitchRepository switchRepository) {
        this.switchRepository = switchRepository;
    }

    @Override
    public Switch getById(String id) {
        return switchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Switch", "id", id));
    }

    @Override
    public Switch save(Switch _switch) {
        return switchRepository.save(_switch);
    }

    @Override
    public void delete(String id) {
        switchRepository.deleteById(id);
    }
}

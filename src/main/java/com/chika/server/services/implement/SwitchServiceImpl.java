package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.product.Switch;
import com.chika.server.repositories.product.SwitchRepository;
import com.chika.server.services.SwitchService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD function for Switch
 * @author Sy Nguyen
 * @version 1.0
 * @since 19-02-2020
 */
@Service
public class SwitchServiceImpl implements SwitchService {

    private final SwitchRepository switchRepository;

    public SwitchServiceImpl(SwitchRepository switchRepository) {
        this.switchRepository = switchRepository;
    }

    @Override
    @Transactional
    public List<Switch> getAll() {
        return switchRepository.findAllByOrderByCreatedAtDesc();
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
    public Switch updateName(String id, String name) {
        Switch _switch = getById(id);
        _switch.setName(name);
        return switchRepository.save(_switch);
    }

    @Override
    public Switch updateUser(String id, Long userId) {
        Switch _switch = getById(id);
        _switch.setUserId(userId);
        return switchRepository.save(_switch);
    }

    @Override
    public void deleteById(String id) {
        switchRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return getById(id).getUserId().equals(userId);
    }
}

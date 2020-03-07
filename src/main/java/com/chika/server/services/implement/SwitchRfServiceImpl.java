package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.product.SwitchRf;
import com.chika.server.repositories.product.SwitchRfRepository;
import com.chika.server.services.product.SwitchRfService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD function for Switch Rf
 * @author Sy Nguyen
 * @version 1.0
 * @since 07-03-2020
 */
@Service
public class SwitchRfServiceImpl implements SwitchRfService {

    private final SwitchRfRepository switchRfRepository;

    public SwitchRfServiceImpl(SwitchRfRepository switchRfRepository) {
        this.switchRfRepository = switchRfRepository;
    }

    @Override
    @Transactional
    public List<SwitchRf> getAll() {
        return switchRfRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional
    public List<SwitchRf> getAllByUserId(Long userId) {
        return switchRfRepository.findAllByUserId(userId);
    }

    @Override
    public SwitchRf getById(String id) {
        return switchRfRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Switch Rf", "id", id));
    }

    @Override
    public SwitchRf save(SwitchRf switchRf) {
        return switchRfRepository.save(switchRf);
    }

    @Override
    public SwitchRf updateName(String id, String name) {
        SwitchRf switchRf = getById(id);
        switchRf.setName(name);
        return switchRfRepository.save(switchRf);
    }

    @Override
    public SwitchRf updateChannel(String id, Long channel) {
        SwitchRf switchRf = getById(id);
        switchRf.setChannel(channel);
        return switchRfRepository.save(switchRf);
    }

    @Override
    public SwitchRf updateUser(String id, Long userId) {
        SwitchRf switchRf = getById(id);
        switchRf.setUserId(userId);
        return switchRfRepository.save(switchRf);
    }

    @Override
    public void deleteById(String id) {
        switchRfRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return getById(id).getUserId().equals(userId);
    }

    @Override
    public Boolean hasOwner(String id) {
        return getById(id).getUserId() != null;
    }
}

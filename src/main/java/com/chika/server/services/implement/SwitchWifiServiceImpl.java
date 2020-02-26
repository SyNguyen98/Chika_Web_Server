package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.product.SwitchWifi;
import com.chika.server.repositories.product.SwitchWifiRepository;
import com.chika.server.services.product.SwitchWifiService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD function for Switch Wifi
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-02-2020
 */
@Service
public class SwitchWifiServiceImpl implements SwitchWifiService {

    private final SwitchWifiRepository switchWifiRepository;

    public SwitchWifiServiceImpl(SwitchWifiRepository switchWifiRepository) {
        this.switchWifiRepository = switchWifiRepository;
    }

    @Override
    @Transactional
    public List<SwitchWifi> getAll() {
        return switchWifiRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public SwitchWifi getById(String id) {
        return switchWifiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Switch Wifi", "id", id));
    }

    @Override
    public SwitchWifi save(SwitchWifi switchWifi) {
        return switchWifiRepository.save(switchWifi);
    }

    @Override
    public SwitchWifi updateName(String id, String name) {
        SwitchWifi switchWifi = getById(id);
        switchWifi.setName(name);
        return switchWifiRepository.save(switchWifi);
    }

    @Override
    public SwitchWifi updateUser(String id, Long userId) {
        SwitchWifi switchWifi = getById(id);
        switchWifi.setUserId(userId);
        return switchWifiRepository.save(switchWifi);
    }

    @Override
    public void deleteById(String id) {
        switchWifiRepository.deleteById(id);
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

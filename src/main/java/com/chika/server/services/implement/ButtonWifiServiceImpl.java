package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.product.ButtonWifi;
import com.chika.server.repositories.product.ButtonWifiRepository;
import com.chika.server.services.product.ButtonWifiService;
import com.chika.server.services.product.SwitchWifiService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD functions for Button Wifi
 * @author Sy Nguyen
 * @version 1.0
 * @since 25-02-2020
 */
@Service
public class ButtonWifiServiceImpl implements ButtonWifiService {

    private final ButtonWifiRepository buttonWifiRepository;

    private final SwitchWifiService switchWifiService;

    public ButtonWifiServiceImpl(ButtonWifiRepository buttonWifiRepository, SwitchWifiService switchWifiService) {
        this.buttonWifiRepository = buttonWifiRepository;
        this.switchWifiService = switchWifiService;
    }

    @Override
    @Transactional
    public List<ButtonWifi> getAllByRoomId(String roomId) {
        return buttonWifiRepository.findAllByRoomId(roomId);
    }

    @Override
    @Transactional
    public List<ButtonWifi> getAllBySwitchId(String switchId) {
        return buttonWifiRepository.findAllBySwitchWifiId(switchId);
    }

    @Override
    public ButtonWifi getById(String id) {
        return buttonWifiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Button Wifi", "id", id));
    }

    @Override
    public ButtonWifi save(ButtonWifi buttonWifi) {
        return buttonWifiRepository.save(buttonWifi);
    }

    @Override
    public ButtonWifi updateInfo(String id, String name, String roomId) {
        ButtonWifi buttonWifi = getById(id);
        buttonWifi.setName(name);
        buttonWifi.setRoomId(roomId);
        return buttonWifiRepository.save(buttonWifi);
    }

    @Override
    public ButtonWifi updateState(String id, int state) {
        ButtonWifi buttonWifi = getById(id);
        buttonWifi.setState(state);
        return buttonWifiRepository.save(buttonWifi);
    }

    @Override
    public void delete(String id) {
        buttonWifiRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return switchWifiService.isOwner(getById(id).getSwitchWifiId(), userId);
    }
}

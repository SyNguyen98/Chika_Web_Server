package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.product.ButtonRf;
import com.chika.server.repositories.product.ButtonRfRepository;
import com.chika.server.services.product.ButtonRfService;
import com.chika.server.services.product.SwitchRfService;
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
public class ButtonRfServiceImpl implements ButtonRfService {

    private final ButtonRfRepository buttonRfRepository;

    private final SwitchRfService switchRfService;

    public ButtonRfServiceImpl(ButtonRfRepository buttonRfRepository, SwitchRfService switchRfService) {
        this.buttonRfRepository = buttonRfRepository;
        this.switchRfService = switchRfService;
    }

    @Override
    @Transactional
    public List<ButtonRf> getAllByRoomId(String roomId) {
        return buttonRfRepository.findAllByRoomId(roomId);
    }

    @Override
    @Transactional
    public List<ButtonRf> getAllBySwitchId(String switchId) {
        return buttonRfRepository.findAllBySwitchRfId(switchId);
    }

    @Override
    public ButtonRf getById(String id) {
        return buttonRfRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Button Rf", "id", id));
    }

    @Override
    public ButtonRf save(ButtonRf buttonRf) {
        return buttonRfRepository.save(buttonRf);
    }

    @Override
    public ButtonRf updateInfo(String id, String name, String roomId) {
        ButtonRf buttonRf = getById(id);
        buttonRf.setName(name);
        buttonRf.setRoomId(roomId);
        return buttonRfRepository.save(buttonRf);
    }

    @Override
    public ButtonRf updateState(String id, int state) {
        ButtonRf buttonRf = getById(id);
        buttonRf.setState(state);
        return buttonRfRepository.save(buttonRf);
    }

    @Override
    public void delete(String id) {
        buttonRfRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return switchRfService.isOwner(getById(id).getSwitchRfId(), userId);
    }
}

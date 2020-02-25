package com.chika.server.services.product;

import com.chika.server.models.product.ButtonWifi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ButtonWifiService {

    List<ButtonWifi> getAllByRoomId(String roomId);

    List<ButtonWifi> getAllBySwitchId(String switchId);

    ButtonWifi getById(String id);

    ButtonWifi save(ButtonWifi buttonWifi);

    ButtonWifi updateInfo(String id, String name, String roomId);

    ButtonWifi updateState(String id, int state);

    void delete(String id);

    Boolean isOwner(String id, Long userId);
}

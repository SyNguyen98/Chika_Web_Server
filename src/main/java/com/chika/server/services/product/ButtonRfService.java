package com.chika.server.services.product;

import com.chika.server.models.product.ButtonRf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ButtonRfService {

    List<ButtonRf> getAllByRoomId(String roomId);

    List<ButtonRf> getAllBySwitchId(String switchId);

    ButtonRf getById(String id);

    ButtonRf save(ButtonRf buttonRf);

    ButtonRf updateInfo(String id, String name, String roomId);

    ButtonRf updateState(String id, int state);

    void delete(String id);

    Boolean isOwner(String id, Long userId);
}

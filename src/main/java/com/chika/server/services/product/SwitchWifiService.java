package com.chika.server.services.product;

import com.chika.server.models.product.SwitchWifi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SwitchWifiService {

    List<SwitchWifi> getAll();

    SwitchWifi getById(String id);

    SwitchWifi save(SwitchWifi switchWifi);

    SwitchWifi updateName(String id, String name);

    SwitchWifi updateUser(String id, Long userId);

    void deleteById(String id);

    Boolean isOwner(String id, Long userId);

    Boolean hasOwner(String id);
}

package com.chika.server.services.product;

import com.chika.server.models.product.SwitchRf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SwitchRfService {

    List<SwitchRf> getAll();

    List<SwitchRf> getAllByUserId(Long userId);

    SwitchRf getById(String id);

    SwitchRf save(SwitchRf switchRf);

    SwitchRf updateName(String id, String name);

    SwitchRf updateChannel(String id, String channel);

    SwitchRf updateUser(String id, Long userId);

    void deleteById(String id);

    long countAll();

    long countByUserId(Long userId);

    Boolean isOwner(String id, Long userId);

    Boolean hasOwner(String id);
}

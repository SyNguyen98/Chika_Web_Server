package com.chika.server.services.product;

import com.chika.server.models.product.HomeCenter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeCenterService {

    List<HomeCenter> getAll();

    List<HomeCenter> getAllByUserId(Long userId);

    HomeCenter getById(String id);

    HomeCenter save(HomeCenter homeCenter);

    HomeCenter updateName(String id, String name);

    HomeCenter updateUser(String id, Long userId);

    void deleteById(String id);

    Boolean hasOwner(String id);
}

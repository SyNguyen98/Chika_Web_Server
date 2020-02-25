package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.product.HomeCenter;
import com.chika.server.repositories.product.HomeCenterRepository;
import com.chika.server.services.product.HomeCenterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HomeCenterServiceImpl implements HomeCenterService {

    private final HomeCenterRepository homeCenterRepository;

    public HomeCenterServiceImpl(HomeCenterRepository homeCenterRepository) {
        this.homeCenterRepository = homeCenterRepository;
    }

    @Override
    @Transactional
    public List<HomeCenter> getAll() {
        return homeCenterRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public HomeCenter getById(String id) {
        return homeCenterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Home center", "id", id));
    }

    @Override
    public HomeCenter save(HomeCenter homeCenter) {
        return homeCenterRepository.save(homeCenter);
    }

    @Override
    public HomeCenter updateName(String id, String name) {
        HomeCenter homeCenter = getById(id);
        homeCenter.setName(name);
        return homeCenterRepository.save(homeCenter);
    }

    @Override
    public HomeCenter updateUser(String id, Long userId) {
        HomeCenter homeCenter = getById(id);
        homeCenter.setUserId(userId);
        return homeCenterRepository.save(homeCenter);
    }

    @Override
    public void deleteById(String id) {
        homeCenterRepository.deleteById(id);
    }
}

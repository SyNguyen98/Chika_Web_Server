package com.chika.server.services;

import com.chika.server.models.house.Script;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScriptService {

    List<Script> getAllByUserId(Long userId);

    Script save(Script script);

    void deleteById(String id);
}

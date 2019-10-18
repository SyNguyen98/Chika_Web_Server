package com.chika.server.services;

import com.chika.server.models.house.IR;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IrService {

    List<IR> getIrByUserId(Long userId);

    IR getIrById(String id);

    List<IR> saveListIr(Long userId, int quantity);

    IR updateIr(String id, String value);

    void deleteIrByUserId(Long userId);

    Boolean isIrOwner(String id, Long userId);
}

package com.chika.server.services;

import com.chika.server.models.house.RemoteIr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RemoteIrService {

    List<RemoteIr> getAllByRoomId(String roomId);

    RemoteIr getById(String id);

    RemoteIr save(RemoteIr remoteIr);

    RemoteIr updateName(String id, String name);

    void deleteById(String id);
}

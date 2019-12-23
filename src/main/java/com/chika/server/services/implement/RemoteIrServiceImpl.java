package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.RemoteIr;
import com.chika.server.repositories.house.RemoteIrRepository;
import com.chika.server.services.RemoteIrService;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD functions for Remote Ir
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@Service
public class RemoteIrServiceImpl implements RemoteIrService {

    private final RemoteIrRepository remoteIrRepository;

    private final RoomService roomService;

    public RemoteIrServiceImpl(RemoteIrRepository remoteIrRepository, RoomService roomService) {
        this.remoteIrRepository = remoteIrRepository;
        this.roomService = roomService;
    }

    @Override
    @Transactional
    public List<RemoteIr> getAllByRoomId(String roomId) {
        return remoteIrRepository.findAllByRoomId(roomId);
    }

    @Override
    public RemoteIr getById(String id) {
        return remoteIrRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Remote Ir", "id", id));
    }

    @Override
    public RemoteIr save(RemoteIr remoteIr) {
        return remoteIrRepository.save(remoteIr);
    }

    @Override
    public RemoteIr updateName(String id, String name) {
        RemoteIr remote = getById(id);
        remote.setName(name);
        return remoteIrRepository.save(remote);
    }

    @Override
    public void deleteById(String id) {
        remoteIrRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return roomService.isOwner(getById(id).getRoomId(), userId);
    }
}

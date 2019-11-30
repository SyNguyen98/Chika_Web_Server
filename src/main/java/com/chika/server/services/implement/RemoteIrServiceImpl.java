package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.RemoteIr;
import com.chika.server.repositories.house.RemoteIrRepository;
import com.chika.server.services.RemoteIrService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteIrServiceImpl implements RemoteIrService {

    private final RemoteIrRepository remoteIrRepository;

    public RemoteIrServiceImpl(RemoteIrRepository remoteIrRepository) {
        this.remoteIrRepository = remoteIrRepository;
    }

    @Override
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
}

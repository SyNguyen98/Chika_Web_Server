package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.RemoteIr;
import com.chika.server.repositories.house.RemoteIrRepository;
import com.chika.server.services.RemoteIrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RemoteIrServiceImpl implements RemoteIrService {

    @Autowired
    private RemoteIrRepository remoteIrRepository;

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

package com.chika.server.services.implement;

import com.chika.server.models.house.RemoteButton;
import com.chika.server.repositories.house.RemoteButtonRepository;
import com.chika.server.services.RemoteButtonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteButtonServiceImpl implements RemoteButtonService {

    private final RemoteButtonRepository remoteButtonRepository;

    public RemoteButtonServiceImpl(RemoteButtonRepository remoteButtonRepository) {
        this.remoteButtonRepository = remoteButtonRepository;
    }

    @Override
    public List<RemoteButton> getAllByRemoteId(String remoteId) {
        return remoteButtonRepository.findAllByRemoteId(remoteId);
    }

    @Override
    public RemoteButton save(RemoteButton remoteButton) {
        return remoteButtonRepository.save(remoteButton);
    }

    @Override
    public void delete(String buttonId) {
        remoteButtonRepository.deleteById(buttonId);
    }
}

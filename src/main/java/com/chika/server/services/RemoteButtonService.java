package com.chika.server.services;

import com.chika.server.models.house.RemoteButton;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RemoteButtonService {

    List<RemoteButton> getAllByRemoteId(String remoteId);

    RemoteButton save(RemoteButton remoteButton);

    void delete(String buttonId);
}

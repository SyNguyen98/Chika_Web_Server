package com.chika.server.repositories.house;

import com.chika.server.models.house.RemoteButton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemoteButtonRepository extends JpaRepository<RemoteButton, String> {

    List<RemoteButton> findAllByRemoteId(String remoteId);
}

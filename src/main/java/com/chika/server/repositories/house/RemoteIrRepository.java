package com.chika.server.repositories.house;

import com.chika.server.models.house.RemoteIr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-11-2019
 */
@Repository
public interface RemoteIrRepository extends JpaRepository<RemoteIr, String> {

    List<RemoteIr> findAllByRoomId(String roomId);

    void deleteAllByRoomId(String roomId);
}

package com.chika.server.repositories.house;

import com.chika.server.models.house.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 17-03-2019
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    List<Room> findAllByUserIdOrderByCreatedAt(Long userId);
}

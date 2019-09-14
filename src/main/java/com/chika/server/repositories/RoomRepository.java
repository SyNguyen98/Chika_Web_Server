package com.chika.server.repositories;

import com.chika.server.models.house.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-07-2019
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}

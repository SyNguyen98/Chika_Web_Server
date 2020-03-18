package com.chika.server.services;

import com.chika.server.models.house.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    List<Room> getAllByUserId(Long userId);

    Room getById(String id);

    Room save(Room room);

    Room updateRoom(String id, String logo, String name);

    void delete(String id);

    Boolean isOwner(String id, Long userId);
}

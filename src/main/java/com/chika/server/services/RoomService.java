package com.chika.server.services;

import com.chika.server.models.house.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    List<Room> getAllRoomsByUserId(Long userId);

    Room getRoomById(String id);

    Room saveRoom(Room room);

    Room updateRoom(String id, String name);

    void deleteRoom(String id);

    Boolean isRoomOwner(String id, Long userId);
}

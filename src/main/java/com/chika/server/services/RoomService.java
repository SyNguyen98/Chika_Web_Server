package com.chika.server.services;

import com.chika.server.models.house.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    Room findRoomById(Integer id);

    List<Room> findAllRooms();

    Room saveRoom(Room room);

    Room updateRoom(Integer id, String name);

    String deleteRoom(Integer id);
}

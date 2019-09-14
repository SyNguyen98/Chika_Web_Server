package com.chika.server.services.implement;

import com.chika.server.models.house.Room;
import com.chika.server.repositories.RoomRepository;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provide CRUD service about Room
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-07-2019
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room findRoomById(Integer id) {
        if (roomRepository.findById(id).isPresent()) {
            return roomRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Integer id, String name) {
        if (roomRepository.findById(id).isPresent()) {
            Room room = roomRepository.findById(id).get();
            room.setName(name);
            return roomRepository.save(room);
        }
        return null;
    }

    @Override
    public String deleteRoom(Integer id) {
        if (roomRepository.findById(id).isPresent()) {
            Room room = roomRepository.findById(id).get();
            roomRepository.delete(room);
            return "deleted";
        }
        return null;
    }
}

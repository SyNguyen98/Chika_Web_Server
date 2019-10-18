package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Room;
import com.chika.server.repositories.RoomRepository;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public List<Room> getAllRoomsByUserId(Long userId) {
        return roomRepository.findAllByUserId(userId);
    }

    @Override
    public Room getRoomById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(String id, String name) {
        Room room = getRoomById(id);
        room.setName(name);
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Boolean isRoomOwner(String id, Long userId) {
        return getRoomById(id).getUserId().equals(userId);
    }
}

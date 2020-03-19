package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Room;
import com.chika.server.repositories.house.RoomRepository;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CRUD functions for Room
 * @author Sy Nguyen
 * @version 1.0
 * @since 19-03-2019
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public List<Room> getAllByUserId(Long userId) {
        return roomRepository.findAllByUserIdOrderByCreatedAt(userId);
    }

    @Override
    public Room getById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(String id, String logo, String name) {
        Room room = getById(id);
        room.setLogo(logo);
        room.setName(name);
        return roomRepository.save(room);
    }

    @Override
    public void delete(String id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Boolean isOwner(String id, Long userId) {
        return getById(id).getUserId().equals(userId);
    }
}

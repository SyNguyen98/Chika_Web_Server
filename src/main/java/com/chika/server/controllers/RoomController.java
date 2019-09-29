package com.chika.server.controllers;

import com.chika.server.models.house.Room;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 27-09-2019
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{userId}")
    public List<Room> getRoomsByUserId(@PathVariable Long userId) {
        return roomService.getAllRoomsByUserId(userId);
    }

    @PostMapping
    public Room saveRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PutMapping
    public Room updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room.getId(), room.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
    }
}

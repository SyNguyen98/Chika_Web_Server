package com.chika.server.controllers;

import com.chika.server.models.house.Room;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.findAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id) {
        return roomService.findRoomById(id);
    }

    @PostMapping
    public Room saveRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PutMapping("/{id}/{name}")
    public Room updateRoom(@PathVariable(value = "id") Integer id, @PathVariable(value = "name") String name) {
        return roomService.updateRoom(id, name);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        return roomService.deleteRoom(id);
    }
}

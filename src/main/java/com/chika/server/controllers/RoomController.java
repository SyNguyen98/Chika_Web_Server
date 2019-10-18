package com.chika.server.controllers;

import com.chika.server.models.house.Room;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 12-10-2019
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getRoomsByUserId(@CurrentUser UserPrincipal currentUser) {
        return roomService.getAllRoomsByUserId(currentUser.getId());
    }

    @PostMapping("/{name}")
    public Room saveRoom(@CurrentUser UserPrincipal currentUser, @PathVariable String name) {
        return roomService.saveRoom(new Room(name, currentUser.getId()));
    }

    @PutMapping
    public Room updateRoom(@CurrentUser UserPrincipal currentUser, @RequestBody Room room) {
        if (roomService.isRoomOwner(room.getId(), currentUser.getId())) {
            return roomService.updateRoom(room.getId(), room.getName());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteRoom(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (roomService.isRoomOwner(id, currentUser.getId())) {
            roomService.deleteRoom(id);
            return true;
        }
        return false;
    }
}

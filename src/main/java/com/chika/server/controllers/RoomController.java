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
 * @since 27-09-2019
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PreAuthorize("#userPrincipal.id == #userId")
    @GetMapping("/{userId}")
    public List<Room> getRoomsByUserId(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long userId) {
        return roomService.getAllRoomsByUserId(userId);
    }

    @PreAuthorize("#userPrincipal.id == #room.userId")
    @PostMapping
    public Room saveRoom(@CurrentUser UserPrincipal userPrincipal, @RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PreAuthorize("#userPrincipal.id == #room.userId")
    @PutMapping
    public Room updateRoom(@CurrentUser UserPrincipal userPrincipal, @RequestBody Room room) {
        return roomService.updateRoom(room.getId(), room.getName());
    }

    @PreAuthorize("#userPrincipal.id == #room.userId")
    @DeleteMapping
    public void deleteRoom(@CurrentUser UserPrincipal userPrincipal, @RequestBody Room room) {
        roomService.deleteRoom(room.getId());
    }
}

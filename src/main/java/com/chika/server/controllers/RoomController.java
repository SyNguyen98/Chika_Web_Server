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
 * @since 20-11-2019
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getByUserId(@CurrentUser UserPrincipal currentUser) {
        return roomService.getAllByUserId(currentUser.getId());
    }

    @PostMapping("/{name}")
    public Room save(@CurrentUser UserPrincipal currentUser, @PathVariable String name) {
        return roomService.save(new Room(name, currentUser.getId()));
    }

    @PutMapping
    public Room update(@CurrentUser UserPrincipal currentUser, @RequestBody Room room) {
        if (roomService.isOwner(room.getId(), currentUser.getId())) {
            return roomService.updateName(room.getId(), room.getName());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (roomService.isOwner(id, currentUser.getId())) {
            roomService.delete(id);
            return true;
        }
        return false;
    }
}

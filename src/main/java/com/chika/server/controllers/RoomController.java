package com.chika.server.controllers;

import com.chika.server.models.house.Room;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Switch requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllByUserId(@CurrentUser UserPrincipal currentUser) {
        return roomService.getAllByUserId(currentUser.getId());
    }

    @PostMapping
    public Room save(@CurrentUser UserPrincipal currentUser, @RequestParam("name") String name) {
        return roomService.save(new Room(name, currentUser.getId()));
    }

    @PutMapping
    public ResponseEntity<?> updateName(@CurrentUser UserPrincipal currentUser, @RequestBody Room room) {
        if (roomService.isOwner(room.getId(), currentUser.getId())) {
            return ResponseEntity.ok(roomService.updateName(room.getId(), room.getName()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not room owner"),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (roomService.isOwner(id, currentUser.getId())) {
            roomService.delete(id);
            return ResponseEntity.ok(new ApiResponse(true, "Room has been deleted"));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not room owner"),
                HttpStatus.BAD_REQUEST);
    }
}

package com.chika.server.controllers;

import com.chika.server.models.house.Room;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.house.RoomResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Switch requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 19-03-2020
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomResponse> getAllByUserId(@CurrentUser UserPrincipal currentUser) {
        return roomService.getAllByUserId(currentUser.getId())
                .stream()
                .map(RoomResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RoomResponse save(@CurrentUser UserPrincipal currentUser, @RequestBody Room room) {
        room.setUserId(currentUser.getId());
        return new RoomResponse(roomService.save(room));
    }

    @PutMapping
    public ResponseEntity<?> updateRoom(@CurrentUser UserPrincipal currentUser, @RequestBody Room room) {
        if (roomService.isOwner(room.getId(), currentUser.getId())) {
            return ResponseEntity.ok(roomService.updateRoom(room.getId(), room.getLogo(), room.getName()));
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

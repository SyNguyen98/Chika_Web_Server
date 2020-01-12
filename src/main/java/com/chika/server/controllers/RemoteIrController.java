package com.chika.server.controllers;

import com.chika.server.models.house.RemoteIr;
import com.chika.server.payload.requests.RemoteIrRequest;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.IrValueService;
import com.chika.server.services.RemoteIrService;
import com.chika.server.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * To receive Remote Ir requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 05-01-2020
 */
@RestController
@RequestMapping("/remote_ir")
public class RemoteIrController {

    private final RemoteIrService remoteIrService;

    private final IrValueService irValueService;

    private final RoomService roomService;

    public RemoteIrController(RemoteIrService remoteIrService, IrValueService irValueService, RoomService roomService) {
        this.remoteIrService = remoteIrService;
        this.irValueService = irValueService;
        this.roomService = roomService;
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<?> getAllRemotesByRoomId(@CurrentUser UserPrincipal currentUser, @PathVariable String roomId) {
        if (roomService.isOwner(roomId, currentUser.getId())) {
            return ResponseEntity.ok(remoteIrService.getAllByRoomId(roomId));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not room owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping()
    public ResponseEntity<?> saveRemote(@CurrentUser UserPrincipal currentUser, @RequestBody RemoteIrRequest request) {
        if (roomService.isOwner(request.getRoomId(), currentUser.getId())) {
            RemoteIr remote = remoteIrService.save(new RemoteIr(request.getName(), request.getModuleId(), request.getRoomId()));
            remote.setIrValues(irValueService.saveList(remote.getId(), request.getNumOfButton()));
            return ResponseEntity.ok(remote);
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not room owner"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> updateName(@CurrentUser UserPrincipal currentUser, @RequestBody RemoteIr remoteIr) {
        if (remoteIrService.isOwner(remoteIr.getId(), currentUser.getId())) {
            return ResponseEntity.ok(remoteIrService.updateName(remoteIr.getId(), remoteIr.getName()));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not remote owner"),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRemote(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (remoteIrService.isOwner(id, currentUser.getId())) {
            irValueService.deleteAllByRemoteIrId(id);
            remoteIrService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Remote has been deleted"));
        }
        return new ResponseEntity<>(new ApiResponse(false, "You are not remote owner"),
                HttpStatus.BAD_REQUEST);
    }
}

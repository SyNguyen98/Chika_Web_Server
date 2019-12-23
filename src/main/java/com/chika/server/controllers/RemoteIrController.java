package com.chika.server.controllers;

import com.chika.server.models.house.RemoteIr;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.IrValueService;
import com.chika.server.services.RemoteIrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Remote Ir requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/remoteir")
public class RemoteIrController {

    private final RemoteIrService remoteIrService;

    private final IrValueService irValueService;

    public RemoteIrController(RemoteIrService remoteIrService, IrValueService irValueService) {
        this.remoteIrService = remoteIrService;
        this.irValueService = irValueService;
    }

    @GetMapping("/{roomId}")
    public List<RemoteIr> getAllRemotesByRoomId(@PathVariable String roomId) {
        return remoteIrService.getAllByRoomId(roomId);
    }

    @PostMapping
    public RemoteIr saveRemote(@RequestBody RemoteIr remoteIr, @RequestParam("numOfButton") int numOfButton) {
        RemoteIr remote = remoteIrService.save(remoteIr);
        remote.setIrValues(irValueService.saveList(remote.getId(), numOfButton));
        return remote;
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

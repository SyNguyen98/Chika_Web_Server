package com.chika.server.controllers;

import com.chika.server.models.house.RemoteIr;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.IrValueService;
import com.chika.server.services.RemoteIrService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 30-11-2019
 */
@RestController
@RequestMapping("/remote-ir")
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
    public RemoteIr saveRemote(@CurrentUser UserPrincipal currentUser,
                               @RequestBody RemoteIr remoteIr,
                               @RequestParam("numOfButton") int numOfButton) {
        RemoteIr remote = remoteIrService.save(new RemoteIr(remoteIr.getName(), remoteIr.getModuleId(),
                remoteIr.getRoomId(), currentUser.getId()));
        remote.setIrValues(irValueService.saveList(remote.getId(), numOfButton));
        return remote;
    }

    @PutMapping
    public RemoteIr updateName(@RequestBody RemoteIr remoteIr) {
        return remoteIrService.updateName(remoteIr.getId(), remoteIr.getName());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteRemote(@PathVariable String id) {
        irValueService.deleteAllByRemoteIrId(id);
        remoteIrService.deleteById(id);
        return true;
    }
}

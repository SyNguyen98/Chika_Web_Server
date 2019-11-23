package com.chika.server.controllers;

import com.chika.server.models.house.RemoteIr;
import com.chika.server.services.IrValueService;
import com.chika.server.services.RemoteIrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remoteIr")
public class RemoteIrController {

    @Autowired
    private RemoteIrService remoteIrService;

    @Autowired
    private IrValueService irValueService;

    @GetMapping("/{roomId}")
    public List<RemoteIr> getAllRemotesByRoomId(@PathVariable String roomId) {
        return remoteIrService.getAllByRoomId(roomId);
    }

    @PostMapping
    public RemoteIr saveRemote(@RequestBody RemoteIr remoteIr, @RequestParam("numOfButton") int numOfButton) {
        RemoteIr remote = remoteIrService.save(remoteIr);
        irValueService.saveList(remote.getId(), numOfButton);
        return remote;
    }

    @PutMapping
    public RemoteIr updateName(@RequestBody RemoteIr remoteIr) {
        return remoteIrService.updateName(remoteIr.getId(), remoteIr.getName());
    }

    @DeleteMapping("/{remoteId}")
    public Boolean deleteRemote(@PathVariable String remoteId) {
        irValueService.deleteAllByRemoteIrId(remoteId);
        remoteIrService.deleteById(remoteId);
        return true;
    }
}

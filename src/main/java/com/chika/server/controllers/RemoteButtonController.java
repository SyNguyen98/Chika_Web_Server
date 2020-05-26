package com.chika.server.controllers;

import com.chika.server.models.house.RemoteButton;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.services.RemoteButtonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Remote Button requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 21-05-2020
 */
@RestController
@RequestMapping("remote-button")
public class RemoteButtonController {

    private final RemoteButtonService remoteButtonService;

    public RemoteButtonController(RemoteButtonService remoteButtonService) {
        this.remoteButtonService = remoteButtonService;
    }

    @GetMapping("remote/{remoteId}")
    public List<RemoteButton> getAllButtonByRemoteId(@PathVariable String remoteId) {
        return remoteButtonService.getAllByRemoteId(remoteId);
    }

    @PostMapping
    public RemoteButton saveButton(@RequestBody RemoteButton remoteButton) {
        return remoteButtonService.save(remoteButton);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteButton(@PathVariable String id) {
        remoteButtonService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Button has been deleted"));
    }
}

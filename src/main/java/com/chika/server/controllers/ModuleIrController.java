package com.chika.server.controllers;

import com.chika.server.models.device.ModuleIr;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.ModuleIrResponse;
import com.chika.server.services.ModuleIrService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To receive Module Ir requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 23-12-2019
 */
@RestController
@RequestMapping("/moduleir")
public class ModuleIrController {

    private final ModuleIrService moduleIrService;

    public ModuleIrController(ModuleIrService moduleIrService) {
        this.moduleIrService = moduleIrService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<ModuleIr> getAll() {
        return moduleIrService.getAll();
    }

    @GetMapping("/{id}")
    public ModuleIr getById(@PathVariable String id) {
        return moduleIrService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ModuleIrResponse save() {
        return new ModuleIrResponse(moduleIrService.save(new ModuleIr("CA-IRX")));
    }

    @PutMapping("/name")
    public ModuleIr updateName(@RequestBody ModuleIr moduleIr) {
        return moduleIrService.updateName(moduleIr.getId(), moduleIr.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/user")
    public ModuleIr updateUser(@RequestBody ModuleIr moduleIr) {
        return moduleIrService.updateUser(moduleIr.getId(), moduleIr.getUserId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        moduleIrService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Module Ir has been deleted"));
    }
}

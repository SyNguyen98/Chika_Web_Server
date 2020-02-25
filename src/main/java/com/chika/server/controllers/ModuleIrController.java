package com.chika.server.controllers;

import com.chika.server.models.product.ModuleIr;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.products.ModuleIrResponse;
import com.chika.server.payload.responses.products.ModuleIrResponseForAdmin;
import com.chika.server.services.product.ModuleIrService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive Module Ir requests from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 19-02-2020
 */
@RestController
@RequestMapping("/module_ir")
public class ModuleIrController {

    private final ModuleIrService moduleIrService;

    public ModuleIrController(ModuleIrService moduleIrService) {
        this.moduleIrService = moduleIrService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<ModuleIrResponseForAdmin> getAll() {
        return moduleIrService.getAll().stream()
                .map(ModuleIrResponseForAdmin::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ModuleIrResponse getById(@PathVariable String id) {
        return new ModuleIrResponse(moduleIrService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ModuleIrResponseForAdmin save() {
        return new ModuleIrResponseForAdmin(moduleIrService.save(new ModuleIr()));
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

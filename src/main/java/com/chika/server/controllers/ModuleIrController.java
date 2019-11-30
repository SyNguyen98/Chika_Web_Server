package com.chika.server.controllers;

import com.chika.server.ChikaWebServerApplication;
import com.chika.server.models.device.ModuleIr;
import com.chika.server.services.ModuleIrService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module-ir")
public class ModuleIrController {

    private final ModuleIrService moduleIrService;

    public ModuleIrController(ModuleIrService moduleIrService) {
        this.moduleIrService = moduleIrService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ModuleIr saveModuleIr() {
        ModuleIr moduleIr = moduleIrService.save(new ModuleIr());
        return moduleIr;
    }
}

package com.chika.server.controllers;

import com.chika.server.models.house.IR;
import com.chika.server.payload.requests.IrRequest;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.IrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ir")
public class IrController {

    @Autowired
    private IrService irService;

    @GetMapping
    public List<IR> getAllIrByUserId(@CurrentUser UserPrincipal currentUser) {
        return irService.getIrByUserId(currentUser.getId());
    }

    @PostMapping
    public List<IR> saveAllIr(@CurrentUser UserPrincipal currentUser, @RequestParam int quantity) {
        return irService.saveListIr(currentUser.getId(), quantity);
    }

    @PutMapping
    public IR updateIr(@CurrentUser UserPrincipal currentUser, @RequestBody IR ir) {
        if (irService.isIrOwner(ir.getId(), currentUser.getId())) {
            return irService.updateIr(ir.getId(), ir.getValue());
        }
        return null;
    }

    @DeleteMapping
    public void deleteAllIrByUserId(@CurrentUser UserPrincipal currentUser) {
        irService.deleteIrByUserId(currentUser.getId());
    }
}

package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.ModuleIr;
import lombok.Data;

@Data
public class ModuleIrResponseForAdmin {

    private String id;
    private String day;
    private Long userId;

    public ModuleIrResponseForAdmin(ModuleIr moduleIr) {
        this.id = moduleIr.getId();
        this.day = Formatter.formatDay(moduleIr.getCreatedAt());
        this.userId = moduleIr.getUserId();
    }
}

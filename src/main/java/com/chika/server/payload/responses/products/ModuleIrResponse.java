package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.ModuleIr;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModuleIrResponse {

    private String id;

    private String name;

    private String time;

    private Long userId;

    public ModuleIrResponse(ModuleIr moduleIr) {
        this.id = moduleIr.getId();
        this.name = moduleIr.getName();
        this.time = Formatter.formatTimeFull(moduleIr.getCreatedAt());
        this.userId = moduleIr.getUserId();
    }
}

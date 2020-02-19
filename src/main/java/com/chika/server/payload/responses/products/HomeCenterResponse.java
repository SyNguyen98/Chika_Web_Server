package com.chika.server.payload.responses.products;

import com.chika.server.models.product.HomeCenter;
import lombok.Data;

@Data
public class HomeCenterResponse {

    private String id;
    private String name;
    private Long userId;

    public HomeCenterResponse(HomeCenter homeCenter) {
        this.id = homeCenter.getId();
        this.name = homeCenter.getName();
        this.userId = homeCenter.getUserId();
    }
}

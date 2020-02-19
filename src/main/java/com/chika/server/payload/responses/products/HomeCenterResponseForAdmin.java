package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.HomeCenter;
import lombok.Data;

@Data
public class HomeCenterResponseForAdmin {

    private String id;
    private String day;
    private Long userId;

    public HomeCenterResponseForAdmin(HomeCenter homeCenter) {
        this.id = homeCenter.getId();
        this.day = Formatter.formatTimeDay(homeCenter.getCreatedAt());
        this.userId = homeCenter.getUserId();
    }
}

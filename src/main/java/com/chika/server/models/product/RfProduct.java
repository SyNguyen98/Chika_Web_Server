package com.chika.server.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RfProduct {

    private String id;

    private String type;

    private Long rfChannel;
}

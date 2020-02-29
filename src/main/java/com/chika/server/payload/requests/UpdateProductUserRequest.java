package com.chika.server.payload.requests;

import com.chika.server.models.product.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateProductUserRequest {

    @NotBlank
    private String userPhone;

    @NotNull
    private List<Product> products;
}

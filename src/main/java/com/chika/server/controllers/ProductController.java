package com.chika.server.controllers;

import com.chika.server.models.account.User;
import com.chika.server.models.product.Product;
import com.chika.server.payload.requests.UpdateProductUserRequest;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.services.UserService;
import com.chika.server.services.product.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * To receive Product requests from the client
 * @author Sy Nguyen
 * @version 1.0
 * @since 03-03-2020
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private final UserService userService;
    private final ProductService productService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<?> updateProductWithUser(@Valid @RequestBody UpdateProductUserRequest request) {
        List<Product> products = request.getProducts();
        String productName = productService.checkListProduct(products);
        if (!productName.equals("")) {
            return new ResponseEntity<>(new ApiResponse(false, productName + " already have owner!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = userService.getByPhone(request.getUserPhone());
        productService.updateProductWithUserId(user.getId(), products);

        return ResponseEntity.ok(new ApiResponse(true, "Product has been updated with user's id"));
    }
}

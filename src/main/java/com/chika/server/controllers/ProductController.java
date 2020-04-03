package com.chika.server.controllers;

import com.chika.server.models.account.User;
import com.chika.server.models.product.Product;
import com.chika.server.payload.requests.UpdateProductUserRequest;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.UserService;
import com.chika.server.services.product.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * To receive Product requests from the client
 * @author Sy Nguyen
 * @version 1.0
 * @since 03-04-2020
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/{userId}")
    public List<Product> getAllProductByUserIdForAdmin(@PathVariable Long userId) {
        return productService.getAllByUserId(userId);
    }

    @GetMapping
    public List<Product> getAllProductByUserId(@CurrentUser UserPrincipal currentUser) {
        return productService.getAllByUserId(currentUser.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/number")
    public Map<String, Long> getAllNumberOfProduct() {
        return productService.getAllNumberOfProduct();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/number/user_id/{userId}")
    public Map<String, Long> getAllNumberOfProductByUserId(@PathVariable Long userId) {
        return productService.getAllNumberOfProductByUserId(userId);
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

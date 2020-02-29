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
 * @since 29-02-2020
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private final UserService userService;
    private final SwitchWifiService switchWifiService;
    private final SwitchRfService switchRfService;
    private final ModuleIrService moduleIrService;
    private final HomeCenterService homeCenterService;
    private final SensorService sensorService;

    public ProductController(SwitchWifiService switchWifiService, SwitchRfService switchRfService, ModuleIrService moduleIrService, HomeCenterService homeCenterService, SensorService sensorService, UserService userService) {
        this.switchWifiService = switchWifiService;
        this.switchRfService = switchRfService;
        this.moduleIrService = moduleIrService;
        this.homeCenterService = homeCenterService;
        this.sensorService = sensorService;
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<?> updateProductWithUser(@Valid @RequestBody UpdateProductUserRequest request) {
        List<Product> products = request.getProducts();
        String productName = checkListProduct(products);
        if (!productName.equals("")) {
            return new ResponseEntity<>(new ApiResponse(false, productName + " already have owner!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = userService.getByPhone(request.getUserPhone());
        updateProductWithUserId(user.getId(), products);

        return ResponseEntity.ok(new ApiResponse(true, "Product has been updated with user's id"));
    }

    String checkListProduct(List<Product> products) {
        for (Product product : products) {
            switch (product.getName()) {
                case "Switch Wifi": {
                    for (String id : product.getIds()) {
                        if (switchWifiService.hasOwner(id)) {
                            return "Switch Wifi";
                        }
                    }
                    break;
                }
                case "Switch Rf": {
                    for (String id : product.getIds()) {
                        if (switchRfService.hasOwner(id)) {
                            return "Switch Rf";
                        }
                    }
                    break;
                }
                case "Module Ir": {
                    for (String id : product.getIds()) {
                        if (moduleIrService.hasOwner(id)) {
                            return "Module Ir";
                        }
                    }
                    break;
                }
                case "Home Center": {
                    for (String id : product.getIds()) {
                        if (homeCenterService.hasOwner(id)) {
                            return "Home Center";
                        }
                    }
                    break;
                }
                case "Sensor": {
                    for (String id : product.getIds()) {
                        if (sensorService.hasOwner(id)) {
                            return "Sensor";
                        }
                    }
                    break;
                }
            }
        }
        return "";
    }

    void updateProductWithUserId(Long userId, List<Product> products) {
        products.forEach(product -> {
            switch (product.getName()) {
                case "Switch Wifi": {
                    product.getIds().forEach(id -> switchWifiService.updateUser(id, userId));
                    break;
                }
                case "Switch Rf": {
                    product.getIds().forEach(id -> switchRfService.updateUser(id, userId));
                    break;
                }
                case "Module Ir": {
                    product.getIds().forEach(id -> moduleIrService.updateUser(id, userId));
                    break;
                }
                case "Home Center": {
                    product.getIds().forEach(id -> homeCenterService.updateUser(id, userId));
                    break;
                }
                case "Sensor": {
                    product.getIds().forEach(id -> sensorService.updateUser(id, userId));
                    break;
                }
            }
        });
    }
}

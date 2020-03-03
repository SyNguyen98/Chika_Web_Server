package com.chika.server.services.implement;

import com.chika.server.models.product.Product;
import com.chika.server.services.product.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Working with Chika products
 * @author Sy Nguyen
 * @version 1.0
 * @since 03-03-2020
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final SwitchWifiService switchWifiService;
    private final SwitchRfService switchRfService;
    private final ModuleIrService moduleIrService;
    private final HomeCenterService homeCenterService;
    private final SensorService sensorService;

    public ProductServiceImpl(SwitchWifiService switchWifiService, SwitchRfService switchRfService,
                              ModuleIrService moduleIrService, HomeCenterService homeCenterService,
                              SensorService sensorService) {
        this.switchWifiService = switchWifiService;
        this.switchRfService = switchRfService;
        this.moduleIrService = moduleIrService;
        this.homeCenterService = homeCenterService;
        this.sensorService = sensorService;
    }

    @Override
    public String checkListProduct(List<Product> products) {
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

    @Override
    public void updateProductWithUserId(Long userId, List<Product> products) {
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

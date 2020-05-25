package com.chika.server.services.implement;

import com.chika.server.models.product.*;
import com.chika.server.models.product.ProductResponse;
import com.chika.server.services.product.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Working with Chika products
 * @author Sy Nguyen
 * @version 1.0
 * @since 18-04-2020
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
    public List<ProductResponse> getAllByUserId(Long userId) {
        List<ProductResponse> productResponses = new ArrayList<>();
        switchWifiService.getAllByUserId(userId).forEach(switchWifi ->
                productResponses.add(new ProductResponse(switchWifi.getType(), switchWifi.getName(), switchWifi.getId())));
        switchRfService.getAllByUserId(userId).forEach(switchRf ->
                productResponses.add(new ProductResponse(switchRf.getType(), switchRf.getName(), switchRf.getId())));
        moduleIrService.getAllByUserId(userId).forEach(moduleIr ->
                productResponses.add(new ProductResponse("IRX", moduleIr.getName(), moduleIr.getId())));
//        sensorService.getAllByUserId(userId).forEach(sensor ->
//                productResponses.add(new ProductResponse(sensor.getType(), sensor.getName(), sensor.getId())));
        return productResponses;
    }

    @Override
    public Map<String, Long> getAllNumberOfProduct() {
        Map<String, Long> map = new HashMap<>();
        map.put("switchWifi", switchWifiService.countAll());
        map.put("switchRf", switchRfService.countAll());
        map.put("moduleIr", moduleIrService.countAll());
        map.put("homeCenter", homeCenterService.countAll());
        map.put("sensor", sensorService.countAll());
        return map;
    }

    @Override
    public Map<String, Long> getAllNumberOfProductByUserId(Long userId) {
        Map<String, Long> map = new HashMap<>();
        map.put("switchWifi", switchWifiService.countByUserId(userId));
        map.put("switchRf", switchRfService.countByUserId(userId));
        map.put("moduleIr", moduleIrService.countByUserId(userId));
        map.put("homeCenter", homeCenterService.countByUserId(userId));
        map.put("sensor", sensorService.countByUserId(userId));
        return map;
    }

    @Override
    public List<RfProduct> getAllRfProductByUserId(Long userId) {
        List<SwitchRf> switchRfs = switchRfService.getAllByUserId(userId);
        List<Sensor> sensors = sensorService.getAllByUserIdAndTypeNotLike(userId, "SS01");

        List<RfProduct> rfProducts = new ArrayList<>();
        switchRfs.forEach(switchRf -> rfProducts.add(new RfProduct(switchRf.getId(), "SR", switchRf.getChannel())));
        sensors.forEach(sensor -> rfProducts.add(new RfProduct(sensor.getId(), sensor.getType(), sensor.getRfChannel())));
        return rfProducts;
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
